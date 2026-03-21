package edu.eci.dosw.tech_cup;

import edu.eci.dosw.tech_cup.model.*;
import edu.eci.dosw.tech_cup.enums.*;
import edu.eci.dosw.tech_cup.exception.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Team Tests")
class TeamTest {

    private Team team;

    private StudentPlayer buildPlayer(int id) {
        return new StudentPlayer(id, "Player" + id, "p" + id + "@eci.edu.co",
                20, id, "Nick" + id, "Forward", true,
                TypePlayer.STRIKER, "Systems Engineering");
    }

    @BeforeEach
    void setUp() {
        team = new Team("ECI FC");
    }

    // ─── Constructor ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("Should create team with valid name")
    void shouldCreateTeamWithValidName() {
        assertEquals("ECI FC", team.getName());
        assertEquals(0, team.getMatches());
        assertEquals(0, team.getFairPlay());
        assertTrue(team.getPlayers().isEmpty());
    }

    @Test
    @DisplayName("Should create team with default constructor")
    void shouldCreateTeamWithDefaultConstructor() {
        Team t = new Team();
        assertNotNull(t.getPlayers());
    }

    // ─── addPlayer ───────────────────────────────────────────────────────────

    @Test
    @DisplayName("Should add player to team successfully")
    void shouldAddPlayerToTeamSuccessfully() {
        // Given
        StudentPlayer player = buildPlayer(1);

        // When
        team.addPlayer(player);

        // Then
        assertEquals(1, team.getPlayers().size());
        assertTrue(team.getPlayers().contains(player));
    }

    @Test
    @DisplayName("Should throw exception when adding null player")
    void shouldThrowExceptionWhenAddingNullPlayer() {
        assertThrows(IllegalArgumentException.class,
                () -> team.addPlayer(null));
    }

    @Test
    @DisplayName("Should throw exception when roster exceeds 20 players")
    void shouldThrowExceptionWhenRosterExceedsTwentyPlayers() {
        // Given - fill roster with 20 players
        for (int i = 1; i <= 20; i++) {
            team.addPlayer(buildPlayer(i));
        }

        // When / Then
        assertThrows(IllegalStateException.class,
                () -> team.addPlayer(buildPlayer(21)));
    }

    @Test
    @DisplayName("Should allow exactly 20 players in roster")
    void shouldAllowExactlyTwentyPlayersInRoster() {
        // Given / When
        for (int i = 1; i <= 20; i++) {
            team.addPlayer(buildPlayer(i));
        }

        // Then
        assertEquals(20, team.getPlayers().size());
    }

    // ─── removePlayer ────────────────────────────────────────────────────────

    @Test
    @DisplayName("Should remove existing player from team")
    void shouldRemoveExistingPlayerFromTeam() {
        // Given
        StudentPlayer player = buildPlayer(1);
        team.addPlayer(player);

        // When
        boolean removed = team.removePlayer(player);

        // Then
        assertTrue(removed);
        assertFalse(team.getPlayers().contains(player));
    }

    @Test
    @DisplayName("Should return false when removing non-existing player")
    void shouldReturnFalseWhenRemovingNonExistingPlayer() {
        // Given
        StudentPlayer player = buildPlayer(99);

        // When
        boolean removed = team.removePlayer(player);

        // Then
        assertFalse(removed);
    }

    // ─── setCaptain ──────────────────────────────────────────────────────────

    @Test
    @DisplayName("Should set captain when player belongs to team")
    void shouldSetCaptainWhenPlayerBelongsToTeam() {
        // Given
        StudentPlayer player = buildPlayer(1);
        team.addPlayer(player);

        // When
        team.setCaptain(player);

        // Then
        assertEquals(player, team.getCaptain());
    }

    @Test
    @DisplayName("Should throw exception when setting captain not in team")
    void shouldThrowExceptionWhenSettingCaptainNotInTeam() {
        // Given
        StudentPlayer outsider = buildPlayer(99);

        // When / Then
        assertThrows(IllegalArgumentException.class,
                () -> team.setCaptain(outsider));
    }

    // ─── Setters ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Should set name correctly")
    void shouldSetNameCorrectly() {
        team.setName("New Name FC");
        assertEquals("New Name FC", team.getName());
    }

    @Test
    @DisplayName("Should set matches correctly")
    void shouldSetMatchesCorrectly() {
        team.setMatches(5);
        assertEquals(5, team.getMatches());
    }

    @Test
    @DisplayName("Should set fair play correctly")
    void shouldSetFairPlayCorrectly() {
        team.setFairPlay(10);
        assertEquals(10, team.getFairPlay());
    }

    // ─── toString ────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Should return correct string representation")
    void shouldReturnCorrectStringRepresentation() {
        String result = team.toString();
        assertTrue(result.contains("ECI FC"));
    }
}
