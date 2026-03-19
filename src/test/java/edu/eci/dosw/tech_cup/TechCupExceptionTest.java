package edu.eci.dosw.tech_cup;

import edu.eci.dosw.tech_cup.model.*;
import edu.eci.dosw.tech_cup.enums.*;
import edu.eci.dosw.tech_cup.exception.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TechCupException Tests")
class TechCupExceptionTest {

    // ─── Constructor (message) ────────────────────────────────────────────────

    @Test
    @DisplayName("Should create exception with message")
    void shouldCreateExceptionWithMessage() {
        // Given / When
        TechCupException ex = new TechCupException("Something went wrong");

        // Then
        assertEquals("Something went wrong", ex.getMessage());
    }

    @Test
    @DisplayName("Should create exception with message and cause")
    void shouldCreateExceptionWithMessageAndCause() {
        // Given
        Throwable cause = new RuntimeException("root cause");

        // When
        TechCupException ex = new TechCupException("Wrapper message", cause);

        // Then
        assertEquals("Wrapper message", ex.getMessage());
        assertEquals(cause, ex.getCause());
    }

    // ─── Constant messages ────────────────────────────────────────────────────

    @Test
    @DisplayName("Should expose TOURNAMENT_NULL constant")
    void shouldExposeTournamentNullConstant() {
        assertNotNull(TechCupException.TOURNAMENT_NULL);
        assertFalse(TechCupException.TOURNAMENT_NULL.isBlank());
    }

    @Test
    @DisplayName("Should expose TEAM_NULL constant")
    void shouldExposeTeamNullConstant() {
        assertNotNull(TechCupException.TEAM_NULL);
    }

    @Test
    @DisplayName("Should expose TEAM_NAME_EMPTY constant")
    void shouldExposeTeamNameEmptyConstant() {
        assertNotNull(TechCupException.TEAM_NAME_EMPTY);
    }

    @Test
    @DisplayName("Should expose PLAYER_NULL constant")
    void shouldExposePlayerNullConstant() {
        assertNotNull(TechCupException.PLAYER_NULL);
    }

    @Test
    @DisplayName("Should expose PLAYER_DUPLICATE constant")
    void shouldExposePlayerDuplicateConstant() {
        assertNotNull(TechCupException.PLAYER_DUPLICATE);
    }

    @Test
    @DisplayName("Should expose CAPTAIN_NULL constant")
    void shouldExposeCaptainNullConstant() {
        assertNotNull(TechCupException.CAPTAIN_NULL);
    }

    @Test
    @DisplayName("Should expose MATCH_NULL constant")
    void shouldExposeMatchNullConstant() {
        assertNotNull(TechCupException.MATCH_NULL);
    }

    @Test
    @DisplayName("Should expose LINEUP_NULL constant")
    void shouldExposeLineupNullConstant() {
        assertNotNull(TechCupException.LINEUP_NULL);
    }

    @Test
    @DisplayName("Should expose LINEUP_INCOMPLETE constant")
    void shouldExposeLineupIncompleteConstant() {
        assertNotNull(TechCupException.LINEUP_INCOMPLETE);
    }

    @Test
    @DisplayName("Should expose REFEREE_NULL constant")
    void shouldExposeRefereeNullConstant() {
        assertNotNull(TechCupException.REFEREE_NULL);
    }

    @Test
    @DisplayName("Should expose TEAM_MIN_PLAYERS constant")
    void shouldExposeTeamMinPlayersConstant() {
        assertNotNull(TechCupException.TEAM_MIN_PLAYERS);
    }

    @Test
    @DisplayName("Should expose PENALTY_CARDS_NULL constant")
    void shouldExposePenaltyCardsNullConstant() {
        assertNotNull(TechCupException.PENALTY_CARDS_NULL);
    }

    // ─── Is RuntimeException ─────────────────────────────────────────────────

    @Test
    @DisplayName("Should be instance of RuntimeException")
    void shouldBeInstanceOfRuntimeException() {
        TechCupException ex = new TechCupException("test");
        assertInstanceOf(RuntimeException.class, ex);
    }

    // ─── Can be thrown and caught ─────────────────────────────────────────────

    @Test
    @DisplayName("Should be throwable and catchable")
    void shouldBeThrowableAndCatchable() {
        assertThrows(TechCupException.class, () -> {
            throw new TechCupException(TechCupException.TEAM_NULL);
        });
    }

    @Test
    @DisplayName("Should carry correct constant message when thrown")
    void shouldCarryCorrectConstantMessageWhenThrown() {
        TechCupException ex = assertThrows(TechCupException.class, () -> {
            throw new TechCupException(TechCupException.PLAYER_NULL);
        });
        assertEquals(TechCupException.PLAYER_NULL, ex.getMessage());
    }
}
