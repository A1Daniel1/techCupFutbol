package edu.eci.dosw.tech_cup;

import edu.eci.dosw.tech_cup.model.*;
import edu.eci.dosw.tech_cup.enums.*;
import edu.eci.dosw.tech_cup.exception.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Statistic Tests")
class StatisticTest {

    private Statistic statistic;

    @BeforeEach
    void setUp() {
        statistic = new Statistic();
    }

    private Team buildTeamWithFairPlay(String name, int fairPlay) {
        Team team = new Team(name);
        team.setFairPlay(fairPlay);
        return team;
    }

    // ─── Constructor ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("Should initialize with empty lists")
    void shouldInitializeWithEmptyLists() {
        assertTrue(statistic.getPlayers().isEmpty());
        assertTrue(statistic.getTeams().isEmpty());
        assertTrue(statistic.getMatches().isEmpty());
    }

    // ─── getBestFairPlayTeam ─────────────────────────────────────────────────

    @Test
    @DisplayName("Should return null when no teams are registered")
    void shouldReturnNullWhenNoTeamsRegistered() {
        assertNull(statistic.getBestFairPlayTeam());
    }

    @Test
    @DisplayName("Should return team with highest fair play score")
    void shouldReturnTeamWithHighestFairPlayScore() {
        // Given
        Team teamA = buildTeamWithFairPlay("Team A", 80);
        Team teamB = buildTeamWithFairPlay("Team B", 95);
        Team teamC = buildTeamWithFairPlay("Team C", 70);

        ArrayList<Team> teams = new ArrayList<>();
        teams.add(teamA);
        teams.add(teamB);
        teams.add(teamC);
        statistic.setTeams(teams);

        // When
        Team best = statistic.getBestFairPlayTeam();

        // Then
        assertEquals(teamB, best);
    }

    @Test
    @DisplayName("Should return the only team when one is registered")
    void shouldReturnOnlyTeamWhenOneIsRegistered() {
        // Given
        Team team = buildTeamWithFairPlay("Solo FC", 50);
        ArrayList<Team> teams = new ArrayList<>();
        teams.add(team);
        statistic.setTeams(teams);

        // When / Then
        assertEquals(team, statistic.getBestFairPlayTeam());
    }

    @Test
    @DisplayName("Should return one of the teams when fair play scores are tied")
    void shouldReturnOneTeamWhenFairPlayScoresAreTied() {
        // Given
        Team teamA = buildTeamWithFairPlay("Team A", 90);
        Team teamB = buildTeamWithFairPlay("Team B", 90);
        ArrayList<Team> teams = new ArrayList<>();
        teams.add(teamA);
        teams.add(teamB);
        statistic.setTeams(teams);

        // When
        Team best = statistic.getBestFairPlayTeam();

        // Then
        assertNotNull(best);
        assertEquals(90, best.getFairPlay());
    }

    // ─── getTotalMatches ─────────────────────────────────────────────────────

    @Test
    @DisplayName("Should return zero when no matches registered")
    void shouldReturnZeroWhenNoMatchesRegistered() {
        assertEquals(0, statistic.getTotalMatches());
    }

    @Test
    @DisplayName("Should return correct count of total matches")
    void shouldReturnCorrectCountOfTotalMatches() {
        // Given
        Team t = new Team("ECI FC");
        ArrayList<Match> matches = new ArrayList<>();
        matches.add(new Match(LocalTime.of(10, 0), LocalTime.of(11, 0), t));
        matches.add(new Match(LocalTime.of(12, 0), LocalTime.of(13, 0), t));
        statistic.setMatches(matches);

        // When / Then
        assertEquals(2, statistic.getTotalMatches());
    }

    // ─── Setters ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Should set players list correctly")
    void shouldSetPlayersListCorrectly() {
        // Given
        ArrayList<Player> players = new ArrayList<>();
        players.add(new StudentPlayer(1, "Ana", "ana@eci.edu.co", 20,
                10, "Ani", "Forward", true, TypePlayer.STRIKER, "Systems"));
        statistic.setPlayers(players);

        // Then
        assertEquals(1, statistic.getPlayers().size());
    }

    @Test
    @DisplayName("Should set teams list correctly")
    void shouldSetTeamsListCorrectly() {
        ArrayList<Team> teams = new ArrayList<>();
        teams.add(new Team("ECI FC"));
        statistic.setTeams(teams);
        assertEquals(1, statistic.getTeams().size());
    }

    @Test
    @DisplayName("Should set matches list correctly")
    void shouldSetMatchesListCorrectly() {
        Team t = new Team("ECI FC");
        ArrayList<Match> matches = new ArrayList<>();
        matches.add(new Match(LocalTime.of(10, 0), LocalTime.of(11, 0), t));
        statistic.setMatches(matches);
        assertEquals(1, statistic.getMatches().size());
    }

    // ─── toString ────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Should return correct string representation")
    void shouldReturnCorrectStringRepresentation() {
        String result = statistic.toString();
        assertTrue(result.contains("Statistic"));
    }
}
