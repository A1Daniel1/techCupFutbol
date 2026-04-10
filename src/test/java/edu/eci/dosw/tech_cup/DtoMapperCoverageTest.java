package edu.eci.dosw.tech_cup;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import edu.eci.dosw.tech_cup.dto.Group;
import edu.eci.dosw.tech_cup.dto.Match;
import edu.eci.dosw.tech_cup.dto.PenaltyCards;
import edu.eci.dosw.tech_cup.dto.Player;
import edu.eci.dosw.tech_cup.dto.Statistic;
import edu.eci.dosw.tech_cup.dto.TablePosition;
import edu.eci.dosw.tech_cup.dto.Team;
import edu.eci.dosw.tech_cup.dto.Tournament;
import edu.eci.dosw.tech_cup.dto.User;
import edu.eci.dosw.tech_cup.entities.RoleEntity;
import edu.eci.dosw.tech_cup.entities.UserEntity;
import edu.eci.dosw.tech_cup.enums.TypePlayer;
import edu.eci.dosw.tech_cup.enums.TypeUser;
import edu.eci.dosw.tech_cup.mappers.UserMapper;

class DtoMapperCoverageTest {

    @Test
    void shouldCoverDtoTeamAndPlayer() {
        Team team = new Team("A");
        Player player = new Player(10, "nick", "mid", true, TypePlayer.CENTRAL_MIDFIELDER);
        team.getPlayers().add(player);
        team.setCaptain(player);
        team.setMatches(2);
        team.setFairPlay(4);
        team.setWins(1);
        team.setDraws(1);
        team.setLosses(0);
        team.setPoints(4);
        team.setGoalsFor(3);
        team.setGoalsAgainst(1);
        team.setGoalDifference(2);

        assertEquals("A", team.getName());
        assertEquals(1, team.getPlayers().size());
        assertEquals("nick", team.getCaptain().getNickName());
        assertEquals(4, team.getPoints());
    }

    @Test
    void shouldCoverGroupAndMatchDtos() {
        Group group = new Group("G1");
        Team team = new Team("Team1");
        Match match = new Match();
        match.setLocalTeam(team);
        match.setAwayTeam(new Team("Team2"));
        match.setGoals(2);
        match.setFaults(3);
        match.setExtraTime(5);
        match.setStartTime(LocalTime.NOON);
        match.setEndTime(LocalTime.NOON.plusHours(2));

        group.addTeam(team);
        group.addMatch(match);

        assertEquals("G1", group.getName());
        assertEquals(1, group.getTeams().size());
        assertEquals(1, group.getMatches().size());

        assertThrows(IllegalArgumentException.class, () -> group.addTeam(null));
        assertThrows(IllegalArgumentException.class, () -> group.addMatch(null));
    }

    @Test
    void shouldCoverTournamentStatisticAndTablePositionDtos() {
        Tournament tournament = new Tournament();
        tournament.setName("Copa");
        tournament.setStartDate(LocalDate.of(2026, 1, 1));
        tournament.setEndDate(LocalDate.of(2026, 1, 31));
        tournament.setStartTime(LocalTime.of(8, 0));
        tournament.setEndTime(LocalTime.of(18, 0));

        Statistic statistic = new Statistic();
        TablePosition tablePosition = new TablePosition();

        tournament.setStatistic(statistic);
        tournament.setTablePosition(tablePosition);
        tournament.setUsers(new ArrayList<>());
        tournament.setTeams(new ArrayList<>());
        tournament.setGroups(new ArrayList<>());
        tournament.setMatches(new ArrayList<>());
        tournament.setReferee(new User());

        assertEquals("Copa", tournament.getName());
        assertNotNull(tournament.getUsers());
        assertNotNull(tournament.getStatistic());

        PenaltyCards cards = new PenaltyCards();
        cards.setYellowCard(1);
        cards.setRedCard(2);
        assertEquals(1, cards.getYellowCard());
        assertEquals(2, cards.getRedCard());

        tablePosition.setTeams(new ArrayList<>());
        assertNotNull(tablePosition.getTeams());
    }

    @Test
    void shouldCoverUserMapperConversions() {
        UserMapper mapper = new UserMapper();
        User dto = new User();
        dto.setName("Ana");
        dto.setEmail("ana@eci.edu.co");
        dto.setAge(20);
        dto.setRole(TypeUser.GEST);

        UserEntity entity = mapper.toEntity(dto, "secret", "Sistemas");
        assertEquals("Ana", entity.getName());
        assertEquals("secret", entity.getPassword());
        assertEquals(1, entity.getRoles().size());

        RoleEntity role = new RoleEntity();
        role.setName("GEST");
        entity.setRoles(new HashSet<>());
        entity.getRoles().add(role);
        entity.setId(33L);

        User mappedDto = mapper.toDto(entity);
        assertEquals(33, mappedDto.getId());
        assertEquals(TypeUser.GEST, mappedDto.getRole());

        mapper.apply(entity, dto, "newSecret", "Industrial");
        assertEquals("newSecret", entity.getPassword());
        assertTrue(entity.getRoles().stream().anyMatch(r -> "GEST".equals(r.getName())));
    }
}
