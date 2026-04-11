package edu.eci.dosw.tech_cup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import edu.eci.dosw.tech_cup.controller.TeamController;
import edu.eci.dosw.tech_cup.exception.TechCupException;
import edu.eci.dosw.tech_cup.model.Team;
import edu.eci.dosw.tech_cup.services.TeamService;

@ExtendWith(MockitoExtension.class)
class TeamControllerTest {

    @Mock
    private TeamService teamService;

    @InjectMocks
    private TeamController teamController;

    @Test
    void shouldCreateTeam() throws Exception {
    Team team = new Team("Los Tigres");
    TeamService.PersistedTeam persisted = new TeamService.PersistedTeam(1L, team);
    when(teamService.createTeam("Los Tigres")).thenReturn(persisted);

    TeamController.TeamResponse response = teamController.createTeam(new TeamController.CreateTeamRequest("Los Tigres"));

    assertEquals(1L, response.id());
    assertEquals("Los Tigres", response.team().getName());
    }

    @Test
    void shouldReturnBadRequestWhenTeamNameIsInvalid() throws Exception {
    when(teamService.createTeam(""))
        .thenThrow(new TechCupException(TechCupException.TEAM_NAME_EMPTY));

    TechCupException ex = assertThrows(
        TechCupException.class,
        () -> teamController.createTeam(new TeamController.CreateTeamRequest("")));

    assertEquals(TechCupException.TEAM_NAME_EMPTY, ex.getMessage());
    }
}
