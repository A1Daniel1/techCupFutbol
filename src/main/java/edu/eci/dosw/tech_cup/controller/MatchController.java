package edu.eci.dosw.tech_cup.controller;

import edu.eci.dosw.tech_cup.model.LineUp;
import edu.eci.dosw.tech_cup.model.Match;
import edu.eci.dosw.tech_cup.model.PenaltyCards;
import edu.eci.dosw.tech_cup.services.MatchService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/matches")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping("/goal")
    public Match registerGoal(@RequestBody Match match) {
        matchService.registerGoal(match);
        return match;
    }

    @PostMapping("/fault")
    public Match registerFault(@RequestBody Match match) {
        matchService.registerFault(match);
        return match;
    }

    @PostMapping("/yellow-card")
    public PenaltyCards issueYellowCard(@RequestBody MatchCardsRequest request) {
        matchService.issueYellowCard(request.match(), request.cards());
        return request.cards();
    }

    @PostMapping("/red-card")
    public PenaltyCards issueRedCard(@RequestBody MatchCardsRequest request) {
        matchService.issueRedCard(request.match(), request.cards());
        return request.cards();
    }

    @PostMapping("/lineup")
    public Match registerLineUp(@RequestBody MatchLineUpRequest request) {
        matchService.registerLineUp(request.match(), request.lineUp());
        return request.match();
    }

    public record MatchCardsRequest(Match match, PenaltyCards cards) {}

    public record MatchLineUpRequest(Match match, LineUp lineUp) {}
}
