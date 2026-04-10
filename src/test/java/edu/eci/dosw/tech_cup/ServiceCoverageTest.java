package edu.eci.dosw.tech_cup;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import edu.eci.dosw.tech_cup.entities.MatchEntity;
import edu.eci.dosw.tech_cup.entities.TeamEntity;
import edu.eci.dosw.tech_cup.entities.TournamentEntity;
import edu.eci.dosw.tech_cup.enums.TypePlayer;
import edu.eci.dosw.tech_cup.exception.TechCupException;
import edu.eci.dosw.tech_cup.mappers.MatchMapper;
import edu.eci.dosw.tech_cup.mappers.TeamMapper;
import edu.eci.dosw.tech_cup.mappers.TournamentMapper;
import edu.eci.dosw.tech_cup.model.LineUp;
import edu.eci.dosw.tech_cup.model.Match;
import edu.eci.dosw.tech_cup.model.PenaltyCards;
import edu.eci.dosw.tech_cup.model.Referee;
import edu.eci.dosw.tech_cup.model.StudentPlayer;
import edu.eci.dosw.tech_cup.model.Team;
import edu.eci.dosw.tech_cup.model.Tournament;
import edu.eci.dosw.tech_cup.repositories.MatchRepository;
import edu.eci.dosw.tech_cup.repositories.TeamRepository;
import edu.eci.dosw.tech_cup.repositories.TournamentRepository;
import edu.eci.dosw.tech_cup.services.MatchService;
import edu.eci.dosw.tech_cup.services.TeamService;
import edu.eci.dosw.tech_cup.services.TournamentService;

@ExtendWith(MockitoExtension.class)
class ServiceCoverageTest {

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private TeamMapper teamMapper;

    @InjectMocks
    private TeamService teamService;

    @Mock
    private TournamentRepository tournamentRepository;

    @Mock
    private TournamentMapper tournamentMapper;

    @InjectMocks
    private TournamentService tournamentService;

    @Mock
    private MatchRepository matchRepository;

    @Mock
    private MatchMapper matchMapper;

    @InjectMocks
    private MatchService matchService;

    @Test
    void shouldCoverTeamServiceCreateAndGet() {
        Team team = new Team("Leones");
        TeamEntity toSave = new TeamEntity();
        TeamEntity saved = new TeamEntity();
        saved.setId(10L);

        when(teamMapper.toEntity(any(Team.class))).thenReturn(toSave);
        when(teamRepository.save(toSave)).thenReturn(saved);
        when(teamMapper.toModel(saved)).thenReturn(team);
        when(teamRepository.findAll(any(Sort.class))).thenReturn(List.of(saved));

        TeamService.PersistedTeam created = teamService.createTeam("Leones");
        List<TeamService.PersistedTeam> all = teamService.getTeams();

        assertNotNull(created.id());
        assertEquals("Leones", created.team().getName());
        assertEquals(1, all.size());
    }

    @Test
    void shouldCoverTeamServiceValidationBranches() {
        assertThrows(TechCupException.class, () -> teamService.createTeam(""));

        TeamEntity entity = new TeamEntity();
        entity.setId(1L);
        Team team = new Team("A");
        team.addPlayer(buildPlayer(1, true));

        when(teamRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(teamMapper.toModel(entity)).thenReturn(team);

        assertThrows(TechCupException.class, () -> teamService.addPlayerToTeam(1L, null));
        assertThrows(IllegalArgumentException.class, () -> teamService.setCaptain(1L, 5));

        Team duplicateTeam = new Team("A");
        var duplicate = buildPlayer(2, true);
        duplicateTeam.addPlayer(duplicate);
        when(teamMapper.toModel(entity)).thenReturn(duplicateTeam);
        assertThrows(TechCupException.class, () -> teamService.addPlayerToTeam(1L, duplicate));
    }

    @Test
    void shouldCoverTournamentServicePaths() {
        Tournament t = new Tournament(LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 31), LocalTime.NOON, LocalTime.NOON);
        TournamentEntity entity = new TournamentEntity();
        entity.setId(1L);
        TournamentEntity saved = new TournamentEntity();
        saved.setId(1L);

        when(tournamentMapper.toEntity(t)).thenReturn(entity);
        when(tournamentRepository.save(entity)).thenReturn(saved);
        when(tournamentMapper.toModel(saved)).thenReturn(t);
        when(tournamentRepository.findAll(any(Sort.class))).thenReturn(List.of(entity));
        when(tournamentMapper.toModel(entity)).thenReturn(t);

        Tournament created = tournamentService.createTournament(t);
        assertEquals(LocalDate.of(2026, 1, 1), created.getStartDate());

        Team validTeam = new Team("X");
        ArrayList players = new ArrayList();
        for (int i = 0; i < 7; i++) {
            players.add(buildPlayer(10 + i, true));
        }
        validTeam.setPlayers(players);

        Tournament afterTeam = tournamentService.registerTeam(0, validTeam);
        assertNotNull(afterTeam);

        Match match = new Match();
        Tournament afterMatch = tournamentService.scheduleMatch(0, match);
        assertNotNull(afterMatch);

        Referee referee = new Referee();
        Tournament afterReferee = tournamentService.assignReferee(0, referee);
        assertNotNull(afterReferee);

        assertThrows(TechCupException.class, () -> tournamentService.createTournament(null));
        assertThrows(TechCupException.class, () -> tournamentService.registerTeam(10, validTeam));
        assertThrows(TechCupException.class, () -> tournamentService.scheduleMatch(0, null));
        assertThrows(TechCupException.class, () -> tournamentService.assignReferee(0, null));
    }

    @Test
    void shouldCoverMatchServicePaths() {
        Match match = new Match();
        match.setGoals(0);
        match.setFaults(0);

        MatchEntity entity = new MatchEntity();
        when(matchMapper.toEntity(any(Match.class))).thenReturn(entity);
        when(matchRepository.save(entity)).thenReturn(entity);
        when(matchMapper.toModel(entity)).thenReturn(match);

        Match afterGoal = matchService.registerGoal(match);
        assertEquals(1, afterGoal.getGoals());

        Match afterFault = matchService.registerFault(match);
        assertEquals(1, afterFault.getFaults());

        PenaltyCards cards = new PenaltyCards(0, 0);
        PenaltyCards afterYellow = matchService.issueYellowCard(match, cards);
        assertEquals(1, afterYellow.getYellowCard());

        PenaltyCards afterRed = matchService.issueRedCard(match, cards);
        assertEquals(1, afterRed.getRedCard());

        LineUp lineUp = new LineUp();
        ArrayList players = new ArrayList();
        for (int i = 0; i < 11; i++) {
            players.add(buildPlayer(i, true));
        }
        lineUp.setPlayers(players);

        Match afterLineUp = matchService.registerLineUp(match, lineUp);
        assertNotNull(afterLineUp);

        assertThrows(TechCupException.class, () -> matchService.registerGoal(null));
        assertThrows(TechCupException.class, () -> matchService.registerFault(null));
        assertThrows(TechCupException.class, () -> matchService.issueYellowCard(null, cards));
        assertThrows(TechCupException.class, () -> matchService.issueYellowCard(match, null));
        assertThrows(TechCupException.class, () -> matchService.issueRedCard(null, cards));
        assertThrows(TechCupException.class, () -> matchService.issueRedCard(match, null));
        assertThrows(TechCupException.class, () -> matchService.registerLineUp(null, lineUp));
        assertThrows(TechCupException.class, () -> matchService.registerLineUp(match, null));

        LineUp incomplete = new LineUp();
        incomplete.setPlayers(new ArrayList<>());
        assertThrows(TechCupException.class, () -> matchService.registerLineUp(match, incomplete));
    }

    private StudentPlayer buildPlayer(int number, boolean available) {
        return new StudentPlayer(
                number,
                "P" + number,
                "p" + number + "@eci.edu.co",
                20,
                number,
                "nick" + number,
                "mid",
                available,
                TypePlayer.CENTRAL_MIDFIELDER,
                "Sistemas");
    }
}
