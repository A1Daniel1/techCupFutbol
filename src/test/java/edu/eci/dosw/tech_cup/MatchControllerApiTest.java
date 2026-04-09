package edu.eci.dosw.tech_cup;

import edu.eci.dosw.tech_cup.controller.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(GlobalExceptionHandler.class)
class MatchControllerApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldRegisterGoal() throws Exception {
        mockMvc.perform(post("/matches/goal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"goals\":0,\"faults\":0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.goals").value(1));
    }

    @Test
    void shouldIssueYellowCard() throws Exception {
        mockMvc.perform(post("/matches/yellow-card")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"match\":{\"goals\":0},\"cards\":{\"yellowCard\":0,\"redCard\":0}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.yellowCard").value(1));
    }
}
