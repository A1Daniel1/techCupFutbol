package edu.eci.dosw.tech_cup;

import edu.eci.dosw.tech_cup.controller.GlobalExceptionHandler;
import edu.eci.dosw.tech_cup.exception.TechCupException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Import(GlobalExceptionHandler.class)
class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateTeam() throws Exception {
        mockMvc.perform(post("/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Los Tigres\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.team.name").value("Los Tigres"));
    }

    @Test
    void shouldReturnBadRequestWhenTeamNameIsInvalid() throws Exception {
        mockMvc.perform(post("/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value(TechCupException.TEAM_NAME_EMPTY));
    }
}
