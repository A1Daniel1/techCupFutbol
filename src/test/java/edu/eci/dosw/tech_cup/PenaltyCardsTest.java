package edu.eci.dosw.tech_cup;

import edu.eci.dosw.tech_cup.model.*;
import edu.eci.dosw.tech_cup.enums.*;
import edu.eci.dosw.tech_cup.exception.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PenaltyCards Tests")
class PenaltyCardsTest {

    private PenaltyCards penaltyCards;

    @BeforeEach
    void setUp() {
        penaltyCards = new PenaltyCards(0, 0);
    }

    // ─── Constructor & Getters ───────────────────────────────────────────────

    @Test
    @DisplayName("Should create PenaltyCards with valid values")
    void shouldCreatePenaltyCardsWithValidValues() {
        // Given / When
        PenaltyCards cards = new PenaltyCards(2, 1);

        // Then
        assertEquals(2, cards.getYellowCard());
        assertEquals(1, cards.getRedCard());
    }

    @Test
    @DisplayName("Should create PenaltyCards with default constructor")
    void shouldCreatePenaltyCardsWithDefaultConstructor() {
        // Given / When
        PenaltyCards cards = new PenaltyCards();

        // Then
        assertEquals(0, cards.getYellowCard());
        assertEquals(0, cards.getRedCard());
    }

    // ─── addYellowCard ───────────────────────────────────────────────────────

    @Test
    @DisplayName("Should increment yellow card count by one")
    void shouldIncrementYellowCardByOne() {
        // Given
        penaltyCards = new PenaltyCards(1, 0);

        // When
        penaltyCards.addYellowCard();

        // Then
        assertEquals(2, penaltyCards.getYellowCard());
    }

    @Test
    @DisplayName("Should increment yellow card from zero")
    void shouldIncrementYellowCardFromZero() {
        // When
        penaltyCards.addYellowCard();

        // Then
        assertEquals(1, penaltyCards.getYellowCard());
    }

    // ─── addRedCard ──────────────────────────────────────────────────────────

    @Test
    @DisplayName("Should increment red card count by one")
    void shouldIncrementRedCardByOne() {
        // Given
        penaltyCards = new PenaltyCards(0, 1);

        // When
        penaltyCards.addRedCard();

        // Then
        assertEquals(2, penaltyCards.getRedCard());
    }

    @Test
    @DisplayName("Should increment red card from zero")
    void shouldIncrementRedCardFromZero() {
        // When
        penaltyCards.addRedCard();

        // Then
        assertEquals(1, penaltyCards.getRedCard());
    }

    // ─── Setters with validation ─────────────────────────────────────────────

    @Test
    @DisplayName("Should set yellow card to valid value")
    void shouldSetYellowCardToValidValue() {
        // When
        penaltyCards.setYellowCard(3);

        // Then
        assertEquals(3, penaltyCards.getYellowCard());
    }

    @Test
    @DisplayName("Should throw exception when setting negative yellow card")
    void shouldThrowExceptionWhenSettingNegativeYellowCard() {
        // When / Then
        assertThrows(IllegalArgumentException.class,
                () -> penaltyCards.setYellowCard(-1));
    }

    @Test
    @DisplayName("Should set red card to valid value")
    void shouldSetRedCardToValidValue() {
        // When
        penaltyCards.setRedCard(2);

        // Then
        assertEquals(2, penaltyCards.getRedCard());
    }

    @Test
    @DisplayName("Should throw exception when setting negative red card")
    void shouldThrowExceptionWhenSettingNegativeRedCard() {
        // When / Then
        assertThrows(IllegalArgumentException.class,
                () -> penaltyCards.setRedCard(-1));
    }

    // ─── Constructor validation ──────────────────────────────────────────────

    @Test
    @DisplayName("Should throw exception when yellow card is negative in constructor")
    void shouldThrowExceptionWhenNegativeYellowCardInConstructor() {
        assertThrows(IllegalArgumentException.class,
                () -> new PenaltyCards(-1, 0));
    }

    @Test
    @DisplayName("Should throw exception when red card is negative in constructor")
    void shouldThrowExceptionWhenNegativeRedCardInConstructor() {
        assertThrows(IllegalArgumentException.class,
                () -> new PenaltyCards(0, -1));
    }

    // ─── toString ────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Should return correct string representation")
    void shouldReturnCorrectStringRepresentation() {
        // Given
        PenaltyCards cards = new PenaltyCards(2, 1);

        // When
        String result = cards.toString();

        // Then
        assertTrue(result.contains("2"));
        assertTrue(result.contains("1"));
    }
}
