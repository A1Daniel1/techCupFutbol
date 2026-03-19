package edu.eci.dosw.tech_cup;

import edu.eci.dosw.tech_cup.model.*;
import edu.eci.dosw.tech_cup.enums.*;
import edu.eci.dosw.tech_cup.exception.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LineUp Tests")
class LineUpTest {

    private LineUp lineUp;

    private StudentPlayer buildAvailablePlayer(int number) {
        return new StudentPlayer(number, "Player" + number, "p" + number + "@eci.edu.co",
                20, number, "Nick" + number, "Forward", true,
                TypePlayer.STRIKER, "Systems Engineering");
    }

    private StudentPlayer buildUnavailablePlayer(int number) {
        return new StudentPlayer(number, "Player" + number, "p" + number + "@eci.edu.co",
                20, number, "Nick" + number, "Forward", false,
                TypePlayer.STRIKER, "Systems Engineering");
    }

    @BeforeEach
    void setUp() {
        lineUp = new LineUp();
    }

    // ─── addPlayer ───────────────────────────────────────────────────────────

    @Test
    @DisplayName("Should add available player to lineup")
    void shouldAddAvailablePlayerToLineup() {
        // Given
        StudentPlayer player = buildAvailablePlayer(1);

        // When
        lineUp.addPlayer(player);

        // Then
        assertEquals(1, lineUp.getPlayers().size());
        assertTrue(lineUp.getPlayers().contains(player));
    }

    @Test
    @DisplayName("Should throw exception when adding null player")
    void shouldThrowExceptionWhenAddingNullPlayer() {
        assertThrows(IllegalArgumentException.class,
                () -> lineUp.addPlayer(null));
    }

    @Test
    @DisplayName("Should throw exception when adding unavailable player")
    void shouldThrowExceptionWhenAddingUnavailablePlayer() {
        // Given
        StudentPlayer player = buildUnavailablePlayer(1);

        // When / Then
        assertThrows(IllegalStateException.class,
                () -> lineUp.addPlayer(player));
    }

    @Test
    @DisplayName("Should throw exception when lineup exceeds 11 players")
    void shouldThrowExceptionWhenLineupExceedsElevenPlayers() {
        // Given - fill lineup with 11 players
        for (int i = 1; i <= 11; i++) {
            lineUp.addPlayer(buildAvailablePlayer(i));
        }

        // When / Then
        assertThrows(IllegalStateException.class,
                () -> lineUp.addPlayer(buildAvailablePlayer(12)));
    }

    @Test
    @DisplayName("Should allow exactly 11 players")
    void shouldAllowExactlyElevenPlayers() {
        // Given / When
        for (int i = 1; i <= 11; i++) {
            lineUp.addPlayer(buildAvailablePlayer(i));
        }

        // Then
        assertEquals(11, lineUp.getPlayers().size());
    }

    // ─── isComplete ──────────────────────────────────────────────────────────

    @Test
    @DisplayName("Should return false when lineup is empty")
    void shouldReturnFalseWhenLineupIsEmpty() {
        assertFalse(lineUp.isComplete());
    }

    @Test
    @DisplayName("Should return false when lineup has fewer than 11 players")
    void shouldReturnFalseWhenLineupHasFewerThanElevenPlayers() {
        // Given
        for (int i = 1; i <= 5; i++) {
            lineUp.addPlayer(buildAvailablePlayer(i));
        }

        // Then
        assertFalse(lineUp.isComplete());
    }

    @Test
    @DisplayName("Should return true when lineup has exactly 11 players")
    void shouldReturnTrueWhenLineupHasExactlyElevenPlayers() {
        // Given
        for (int i = 1; i <= 11; i++) {
            lineUp.addPlayer(buildAvailablePlayer(i));
        }

        // Then
        assertTrue(lineUp.isComplete());
    }

    // ─── setPlayers & toString ───────────────────────────────────────────────

    @Test
    @DisplayName("Should set players list correctly")
    void shouldSetPlayersListCorrectly() {
        // Given
        java.util.ArrayList<Player> players = new java.util.ArrayList<>();
        players.add(buildAvailablePlayer(1));

        // When
        lineUp.setPlayers(players);

        // Then
        assertEquals(1, lineUp.getPlayers().size());
    }

    @Test
    @DisplayName("Should return correct string representation")
    void shouldReturnCorrectStringRepresentation() {
        // Given
        lineUp.addPlayer(buildAvailablePlayer(1));

        // When
        String result = lineUp.toString();

        // Then
        assertTrue(result.contains("1"));
    }
}