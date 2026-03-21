package edu.eci.dosw.tech_cup.controller;

import edu.eci.dosw.tech_cup.model.LineUp;
import edu.eci.dosw.tech_cup.model.Match;
import edu.eci.dosw.tech_cup.model.PenaltyCards;
import edu.eci.dosw.tech_cup.services.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/matches")
@Tag(name = "Matches", description = "Endpoints para eventos de partidos")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping("/goal")
    @Operation(summary = "Registrar gol", description = "Incrementa la cantidad de goles del partido")
    public Match registerGoal(@RequestBody Match match) {
        matchService.registerGoal(match);
        return match;
    }

    @PostMapping("/fault")
    @Operation(summary = "Registrar falta", description = "Incrementa la cantidad de faltas del partido")
    public Match registerFault(@RequestBody Match match) {
        matchService.registerFault(match);
        return match;
    }

    @PostMapping("/yellow-card")
    @Operation(summary = "Registrar tarjeta amarilla", description = "Suma una tarjeta amarilla en el registro de tarjetas")
    public PenaltyCards issueYellowCard(@RequestBody MatchCardsRequest request) {
        matchService.issueYellowCard(request.match(), request.cards());
        return request.cards();
    }

    @PostMapping("/red-card")
    @Operation(summary = "Registrar tarjeta roja", description = "Suma una tarjeta roja en el registro de tarjetas")
    public PenaltyCards issueRedCard(@RequestBody MatchCardsRequest request) {
        matchService.issueRedCard(request.match(), request.cards());
        return request.cards();
    }

    @PostMapping("/lineup")
    @Operation(summary = "Registrar alineacion", description = "Valida y registra la alineacion para un partido")
    public Match registerLineUp(@RequestBody MatchLineUpRequest request) {
        matchService.registerLineUp(request.match(), request.lineUp());
        return request.match();
    }

    public record MatchCardsRequest(Match match, PenaltyCards cards) {}

    public record MatchLineUpRequest(Match match, LineUp lineUp) {}
}
