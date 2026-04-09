package edu.eci.dosw.tech_cup.controller;

import edu.eci.dosw.tech_cup.model.Match;
import edu.eci.dosw.tech_cup.model.Referee;
import edu.eci.dosw.tech_cup.model.Team;
import edu.eci.dosw.tech_cup.model.Tournament;
import edu.eci.dosw.tech_cup.services.TournamentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tournaments")
@Tag(name = "Tournaments", description = "Endpoints para gestion de torneos")
public class TournamentController {

    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping
    @Operation(summary = "Listar torneos", description = "Retorna la lista de torneos registrados")
    public List<Tournament> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }

    @PostMapping
    @Operation(summary = "Crear torneo", description = "Registra un nuevo torneo")
    public Tournament createTournament(@RequestBody Tournament tournament) {
        return tournamentService.createTournament(tournament);
    }

    @PostMapping("/{tournamentIndex}/teams")
    @Operation(summary = "Registrar equipo en torneo", description = "Registra un equipo en el torneo por indice")
    public Tournament registerTeam(@PathVariable int tournamentIndex, @RequestBody Team team) {
        return tournamentService.registerTeam(tournamentIndex, team);
    }

    @PostMapping("/{tournamentIndex}/matches")
    @Operation(summary = "Programar partido", description = "Programa un partido dentro del torneo por indice")
    public Tournament scheduleMatch(@PathVariable int tournamentIndex, @RequestBody Match match) {
        return tournamentService.scheduleMatch(tournamentIndex, match);
    }

    @PostMapping("/{tournamentIndex}/referee")
    @Operation(summary = "Asignar arbitro", description = "Asigna arbitro al torneo por indice")
    public Tournament assignReferee(@PathVariable int tournamentIndex, @RequestBody Referee referee) {
        return tournamentService.assignReferee(tournamentIndex, referee);
    }
}
