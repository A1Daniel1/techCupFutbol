package edu.eci.dosw.tech_cup.controller;

import edu.eci.dosw.tech_cup.services.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import edu.eci.dosw.tech_cup.model.Player;
import edu.eci.dosw.tech_cup.model.Team;

@RestController
@RequestMapping("/teams")
@Tag(name = "Teams", description = "Endpoints para gestion de equipos")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    @Operation(summary = "Listar equipos", description = "Retorna los equipos creados en memoria durante la ejecucion")
    public List<TeamResponse> getTeams() {
        return teamService.getTeams()
                .stream()
                .map(team -> new TeamResponse(team.id(), team.team()))
                .toList();
    }

    @PostMapping
    @Operation(summary = "Crear equipo", description = "Crea un equipo validando las reglas de TeamService")
    public TeamResponse createTeam(@RequestBody CreateTeamRequest request) {
        TeamService.PersistedTeam createdTeam = teamService.createTeam(request.name());
        return new TeamResponse(createdTeam.id(), createdTeam.team());
    }

    @PostMapping("/{teamId}/players")
    @Operation(summary = "Agregar jugador a equipo", description = "Agrega un jugador al equipo indicado")
    public TeamResponse addPlayer(@PathVariable long teamId, @RequestBody AddPlayerRequest request) {
        TeamService.PersistedTeam updatedTeam = teamService.addPlayerToTeam(teamId, request.player());
        return new TeamResponse(updatedTeam.id(), updatedTeam.team());
    }

    @PostMapping("/{teamId}/captain")
    @Operation(summary = "Definir capitan", description = "Asigna como capitan a un jugador existente dentro del equipo")
    public TeamResponse setCaptain(@PathVariable long teamId, @RequestBody SetCaptainRequest request) {
        TeamService.PersistedTeam updatedTeam = teamService.setCaptain(teamId, request.playerIndex());
        return new TeamResponse(updatedTeam.id(), updatedTeam.team());
    }

    public record CreateTeamRequest(String name) {
    }

    public record AddPlayerRequest(Player player) {
    }

    public record SetCaptainRequest(int playerIndex) {
    }

    public record TeamResponse(Long id, Team team) {
    }
}
