package edu.eci.dosw.tech_cup.services;

import edu.eci.dosw.tech_cup.exception.TechCupException;
import edu.eci.dosw.tech_cup.model.LineUp;
import edu.eci.dosw.tech_cup.model.Match;
import edu.eci.dosw.tech_cup.model.PenaltyCards;

public class MatchService {

    public void registerGoal(Match match) {
        if (match == null) throw new TechCupException(TechCupException.MATCH_NULL);
        match.setGoals(match.getGoals() + 1);
    }

    public void registerFault(Match match) {
        if (match == null) throw new TechCupException(TechCupException.MATCH_NULL);
        match.setFaults(match.getFaults() + 1);
    }

    public void issueYellowCard(Match match, PenaltyCards cards) {
        if (match == null) throw new TechCupException(TechCupException.MATCH_NULL);
        if (cards == null) throw new TechCupException(TechCupException.PENALTY_CARDS_NULL);
        cards.addYellowCard();
    }

    public void issueRedCard(Match match, PenaltyCards cards) {
        if (match == null) throw new TechCupException(TechCupException.MATCH_NULL);
        if (cards == null) throw new TechCupException(TechCupException.PENALTY_CARDS_NULL);
        cards.addRedCard();
    }

    public void registerLineUp(Match match, LineUp lineUp) {
        if (match  == null) throw new TechCupException(TechCupException.MATCH_NULL);
        if (lineUp == null) throw new TechCupException(TechCupException.LINEUP_NULL);
        if (!lineUp.isComplete()) throw new TechCupException(TechCupException.LINEUP_INCOMPLETE);
    }
}
