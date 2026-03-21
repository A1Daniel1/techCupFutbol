package edu.eci.dosw.tech_cup;

import edu.eci.dosw.tech_cup.controller.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(GlobalExceptionHandler.class)
class TournamentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGetAllTournaments() throws Exception {
        mockMvc.perform(get("/tournaments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void shouldCreateTournament() throws Exception {
        mockMvc.perform(post("/tournaments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{\"startDate\":\"2026-03-01\",\"endDate\":\"2026-03-31\",\"startTime\":\"08:00:00\",\"endTime\":\"18:00:00\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.startDate").value("2026-03-01"));
    }
}
