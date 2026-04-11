package edu.eci.dosw.tech_cup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import edu.eci.dosw.tech_cup.entities.UserEntity;
import edu.eci.dosw.tech_cup.enums.TypeUser;
import edu.eci.dosw.tech_cup.repositories.UserRepository;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();

        UserEntity user = new UserEntity();
        user.setName("Usuario Demo");
        user.setEmail("usuario@eci.edu.co");
        user.setAge(20);
        user.setType(TypeUser.PLAYER);
        user.setPassword(passwordEncoder.encode("correcta"));
        user.setAcademicProgram("Ingenieria de Sistemas");
        userRepository.save(user);
    }

    @Test
    void shouldReturnTokenWhenCredentialsAreValid() throws Exception {
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"usuario@eci.edu.co\",\"password\":\"correcta\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty());
    }

    @Test
    void shouldReturnUnauthorizedWhenPasswordIsInvalid() throws Exception {
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"usuario@eci.edu.co\",\"password\":\"incorrecta\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value("Invalid email or password"));
    }
}
