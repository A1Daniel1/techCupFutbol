package edu.eci.dosw.tech_cup;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.eci.dosw.tech_cup.model.Match;
import edu.eci.dosw.tech_cup.model.PenaltyCards;
import edu.eci.dosw.tech_cup.model.Team;

@DisplayName("Match Tests")
class MatchTest {

    private Match match;
    private Team  localTeam;

    @BeforeEach
    void setUp() {
        localTeam = new Team("ECI FC");
        match     = new Match(LocalTime.of(10, 0), LocalTime.of(11, 0), localTeam);
    }

    // ─── Constructor ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("Should create match with valid arguments")
    void shouldCreateMatchWithValidArguments() {
        assertEquals(LocalTime.of(10, 0), match.getStartTime());
        assertEquals(LocalTime.of(11, 0), match.getEndTime());
        assertEquals(localTeam, match.getLocalTeam());
        assertEquals(0, match.getGoals());
        assertEquals(0, match.getFaults());
        assertTrue(match.getPenaltyCards().isEmpty());
    }

    @Test
    @DisplayName("Should create match with default constructor")
    void shouldCreateMatchWithDefaultConstructor() {
        Match m = new Match();
        assertNotNull(m.getPenaltyCards());
    }

    @Test
    @DisplayName("Should throw exception when start time is null")
    void shouldThrowExceptionWhenStartTimeIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Match(null, LocalTime.of(11, 0), localTeam));
    }

    @Test
    @DisplayName("Should throw exception when local team is null")
    void shouldThrowExceptionWhenLocalTeamIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Match(LocalTime.of(10, 0), LocalTime.of(11, 0), null));
    }

    // ─── addPenaltyCards ─────────────────────────────────────────────────────

    @Test
    @DisplayName("Should add penalty cards to match")
    void shouldAddPenaltyCardsToMatch() {
        // Given
        PenaltyCards cards = new PenaltyCards(2, 0);

        // When
        match.addPenaltyCards(cards);

        // Then
        assertEquals(1, match.getPenaltyCards().size());
    }

    @Test
    @DisplayName("Should throw exception when adding null penalty cards")
    void shouldThrowExceptionWhenAddingNullPenaltyCards() {
        assertThrows(IllegalArgumentException.class,
                () -> match.addPenaltyCards(null));
    }

    // ─── getTotalYellowCards ─────────────────────────────────────────────────

    @Test
    @DisplayName("Should return zero total yellow cards when none added")
    void shouldReturnZeroTotalYellowCardsWhenNoneAdded() {
        assertEquals(0, match.getTotalYellowCards());
    }

    @Test
    @DisplayName("Should return correct total yellow cards from multiple entries")
    void shouldReturnCorrectTotalYellowCardsFromMultipleEntries() {
        // Given
        match.addPenaltyCards(new PenaltyCards(2, 0));
        match.addPenaltyCards(new PenaltyCards(3, 0));

        // When / Then
        assertEquals(5, match.getTotalYellowCards());
    }

    // ─── getTotalRedCards ────────────────────────────────────────────────────

    @Test
    @DisplayName("Should return zero total red cards when none added")
    void shouldReturnZeroTotalRedCardsWhenNoneAdded() {
        assertEquals(0, match.getTotalRedCards());
    }

    @Test
    @DisplayName("Should return correct total red cards from multiple entries")
    void shouldReturnCorrectTotalRedCardsFromMultipleEntries() {
        // Given
        match.addPenaltyCards(new PenaltyCards(0, 1));
        match.addPenaltyCards(new PenaltyCards(0, 2));

        // When / Then
        assertEquals(3, match.getTotalRedCards());
    }

    // ─── Setters ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Should set goals correctly")
    void shouldSetGoalsCorrectly() {
        match.setGoals(3);
        assertEquals(3, match.getGoals());
    }

    @Test
    @DisplayName("Should set faults correctly")
    void shouldSetFaultsCorrectly() {
        match.setFaults(5);
        assertEquals(5, match.getFaults());
    }

    @Test
    @DisplayName("Should set extra time correctly")
    void shouldSetExtraTimeCorrectly() {
        match.setExtraTime(match.getExtraTime() + 7);
        assertEquals(7, match.getExtraTime());
    }

    @Test
    @DisplayName("Should set start time correctly")
    void shouldSetStartTimeCorrectly() {
        match.setStartTime(LocalTime.of(9, 0));
        assertEquals(LocalTime.of(9, 0), match.getStartTime());
    }

    @Test
    @DisplayName("Should set end time correctly")
    void shouldSetEndTimeCorrectly() {
        match.setEndTime(LocalTime.of(12, 0));
        assertEquals(LocalTime.of(12, 0), match.getEndTime());
    }

    @Test
    @DisplayName("Should set local team correctly")
    void shouldSetLocalTeamCorrectly() {
        Team newTeam = new Team("New FC");
        match.setLocalTeam(newTeam);
        assertEquals(newTeam, match.getLocalTeam());
    }

    // ─── toString ────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Should return correct string representation")
    void shouldReturnCorrectStringRepresentation() {
        String result = match.toString();
        assertTrue(result.contains("10:00"));
    }
}