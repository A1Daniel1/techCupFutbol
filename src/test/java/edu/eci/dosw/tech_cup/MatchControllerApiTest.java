package edu.eci.dosw.tech_cup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import edu.eci.dosw.tech_cup.controller.MatchController;
import edu.eci.dosw.tech_cup.model.Match;
import edu.eci.dosw.tech_cup.model.PenaltyCards;
import edu.eci.dosw.tech_cup.services.MatchService;

@ExtendWith(MockitoExtension.class)
class MatchControllerApiTest {

    @Mock
    private MatchService matchService;

    @InjectMocks
    private MatchController matchController;

    @Test
    void shouldRegisterGoal() throws Exception {
        Match initialMatch = new Match();
        initialMatch.setGoals(0);
        Match updatedMatch = new Match();
        updatedMatch.setGoals(1);

        when(matchService.registerGoal(initialMatch)).thenReturn(updatedMatch);

        Match response = matchController.registerGoal(initialMatch);

        assertEquals(1, response.getGoals());
    }

    @Test
    void shouldIssueYellowCard() throws Exception {
        Match match = new Match();
        PenaltyCards inputCards = new PenaltyCards(0, 0);
        PenaltyCards updatedCards = new PenaltyCards(1, 0);

        when(matchService.issueYellowCard(match, inputCards)).thenReturn(updatedCards);

        PenaltyCards response = matchController.issueYellowCard(new MatchController.MatchCardsRequest(match, inputCards));

        assertEquals(1, response.getYellowCard());
    }
}
