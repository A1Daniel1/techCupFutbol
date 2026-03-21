package edu.eci.dosw.tech_cup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.eci.dosw.tech_cup.model.TablePosition;
import edu.eci.dosw.tech_cup.model.Team;

@DisplayName("TablePosition Tests")
class TablePositionTest {

    private TablePosition tablePosition;
    private Team teamA;
    private Team teamB;

    @BeforeEach
    void setUp() {
        tablePosition = new TablePosition();
        teamA = new Team("Team A");
        teamB = new Team("Team B");
    }

    // ─── addTeam ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Should add team to table successfully")
    void shouldAddTeamToTableSuccessfully() {
        // When
        tablePosition.addTeam(teamA);

        // Then
        assertEquals(1, tablePosition.getTeams().size());
    }

    @Test
    @DisplayName("Should throw exception when adding null team")
    void shouldThrowExceptionWhenAddingNullTeam() {
        assertThrows(IllegalArgumentException.class,
                () -> tablePosition.addTeam(null));
    }

    @Test
    @DisplayName("Should add multiple teams preserving order")
    void shouldAddMultipleTeamsPreservingOrder() {
        // When
        tablePosition.addTeam(teamA);
        tablePosition.addTeam(teamB);

        // Then
        assertEquals(teamA, tablePosition.getTeams().get(0));
        assertEquals(teamB, tablePosition.getTeams().get(1));
    }

    // ─── getPosition ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("Should return correct position for existing team")
    void shouldReturnCorrectPositionForExistingTeam() {
        // Given
        tablePosition.addTeam(teamA);
        tablePosition.addTeam(teamB);

        // When / Then
        assertEquals(1, tablePosition.getPosition(teamA));
        assertEquals(2, tablePosition.getPosition(teamB));
    }

    @Test
    @DisplayName("Should return minus one when team is not in table")
    void shouldReturnMinusOneWhenTeamIsNotInTable() {
        // Given
        Team outsider = new Team("Ghost FC");

        // When / Then
        assertEquals(-1, tablePosition.getPosition(outsider));
    }

    // ─── getTeamAtPosition ───────────────────────────────────────────────────

    @Test
    @DisplayName("Should return team at valid position")
    void shouldReturnTeamAtValidPosition() {
        // Given
        tablePosition.addTeam(teamA);
        tablePosition.addTeam(teamB);

        // When / Then
        assertEquals(teamA, tablePosition.getTeamAtPosition(1));
        assertEquals(teamB, tablePosition.getTeamAtPosition(2));
    }

    @Test
    @DisplayName("Should throw exception when position is zero")
    void shouldThrowExceptionWhenPositionIsZero() {
        tablePosition.addTeam(teamA);
        assertThrows(IllegalArgumentException.class,
                () -> tablePosition.getTeamAtPosition(0));
    }

    @Test
    @DisplayName("Should throw exception when position is negative")
    void shouldThrowExceptionWhenPositionIsNegative() {
        tablePosition.addTeam(teamA);
        assertThrows(IllegalArgumentException.class,
                () -> tablePosition.getTeamAtPosition(-1));
    }

    @Test
    @DisplayName("Should throw exception when position exceeds table size")
    void shouldThrowExceptionWhenPositionExceedsTableSize() {
        tablePosition.addTeam(teamA);
        assertThrows(IllegalArgumentException.class,
                () -> tablePosition.getTeamAtPosition(2));
    }

    // ─── setTeams & toString ─────────────────────────────────────────────────

    @Test
    @DisplayName("Should set teams list correctly")
    void shouldSetTeamsListCorrectly() {
        // Given
        java.util.ArrayList<Team> teams = new java.util.ArrayList<>();
        teams.add(teamA);

        // When
        tablePosition.setTeams(teams);

        // Then
        assertEquals(1, tablePosition.getTeams().size());
    }

    @Test
    @DisplayName("Should return correct string representation")
    void shouldReturnCorrectStringRepresentation() {
        tablePosition.addTeam(teamA);
        String result = tablePosition.toString();
        assertTrue(result.contains("1"));
    }
}
