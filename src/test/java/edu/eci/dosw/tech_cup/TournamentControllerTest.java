package edu.eci.dosw.tech_cup;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import edu.eci.dosw.tech_cup.controller.TournamentController;
import edu.eci.dosw.tech_cup.model.Tournament;
import edu.eci.dosw.tech_cup.services.TournamentService;

@ExtendWith(MockitoExtension.class)
class TournamentControllerTest {

    @Mock
    private TournamentService tournamentService;

    @InjectMocks
    private TournamentController tournamentController;

    @Test
    void shouldGetAllTournaments() throws Exception {
        Tournament t = new Tournament();
        t.setStartDate(LocalDate.of(2026, 3, 1));
        when(tournamentService.getAllTournaments()).thenReturn(List.of(t));

        List<Tournament> tournaments = tournamentController.getAllTournaments();

        assertFalse(tournaments.isEmpty());
        assertEquals(LocalDate.of(2026, 3, 1), tournaments.get(0).getStartDate());
    }

    @Test
    void shouldCreateTournament() throws Exception {
        Tournament payload = new Tournament();
        payload.setStartDate(LocalDate.of(2026, 3, 1));
        payload.setEndDate(LocalDate.of(2026, 3, 31));
        payload.setStartTime(LocalTime.of(8, 0));
        payload.setEndTime(LocalTime.of(18, 0));

        when(tournamentService.createTournament(payload)).thenReturn(payload);

        Tournament created = tournamentController.createTournament(payload);

        assertEquals(LocalDate.of(2026, 3, 1), created.getStartDate());
    }
}
