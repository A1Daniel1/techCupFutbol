package edu.eci.dosw.tech_cup.services;

import edu.eci.dosw.tech_cup.exception.TechCupException;
import edu.eci.dosw.tech_cup.entities.MatchEntity;
import edu.eci.dosw.tech_cup.mappers.MatchMapper;
import edu.eci.dosw.tech_cup.model.LineUp;
import edu.eci.dosw.tech_cup.model.Match;
import edu.eci.dosw.tech_cup.model.PenaltyCards;
import edu.eci.dosw.tech_cup.repositories.MatchRepository;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;

    public MatchService(MatchRepository matchRepository, MatchMapper matchMapper) {
        this.matchRepository = matchRepository;
        this.matchMapper = matchMapper;
    }

    public Match registerGoal(Match match) {
        if (match == null)
            throw new TechCupException(TechCupException.MATCH_NULL);
        match.setGoals(match.getGoals() + 1);
        return saveMatch(match);
    }

    public Match registerFault(Match match) {
        if (match == null)
            throw new TechCupException(TechCupException.MATCH_NULL);
        match.setFaults(match.getFaults() + 1);
        return saveMatch(match);
    }

    public PenaltyCards issueYellowCard(Match match, PenaltyCards cards) {
        if (match == null)
            throw new TechCupException(TechCupException.MATCH_NULL);
        if (cards == null)
            throw new TechCupException(TechCupException.PENALTY_CARDS_NULL);
        cards.addYellowCard();
        if (!match.getPenaltyCards().contains(cards)) {
            match.addPenaltyCards(cards);
        }
        saveMatch(match);
        return cards;
    }

    public PenaltyCards issueRedCard(Match match, PenaltyCards cards) {
        if (match == null)
            throw new TechCupException(TechCupException.MATCH_NULL);
        if (cards == null)
            throw new TechCupException(TechCupException.PENALTY_CARDS_NULL);
        cards.addRedCard();
        if (!match.getPenaltyCards().contains(cards)) {
            match.addPenaltyCards(cards);
        }
        saveMatch(match);
        return cards;
    }

    public Match registerLineUp(Match match, LineUp lineUp) {
        if (match == null)
            throw new TechCupException(TechCupException.MATCH_NULL);
        if (lineUp == null)
            throw new TechCupException(TechCupException.LINEUP_NULL);
        if (!lineUp.isComplete())
            throw new TechCupException(TechCupException.LINEUP_INCOMPLETE);
        return saveMatch(match);
    }

    private Match saveMatch(Match match) {
        MatchEntity saved = matchRepository.save(matchMapper.toEntity(match));
        return matchMapper.toModel(saved);
    }
}
