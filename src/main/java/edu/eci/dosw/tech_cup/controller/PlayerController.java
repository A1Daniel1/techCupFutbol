package edu.eci.dosw.tech_cup.controller;

import edu.eci.dosw.tech_cup.controller.TeamController.AddPlayerRequest;
import edu.eci.dosw.tech_cup.controller.TeamController.TeamResponse;
import edu.eci.dosw.tech_cup.exception.TechCupException;
import edu.eci.dosw.tech_cup.model.Player;
import edu.eci.dosw.tech_cup.model.Team;
import edu.eci.dosw.tech_cup.services.TeamService;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerController {

    private final TeamService teamService;
    private final Map<Integer, Team> teams = new ConcurrentHashMap<>();

    public PlayerController(TeamService teamService) {
        this.teamService = teamService;
    }

    private Team getTeamOrThrow(int teamId) {
        Team team = teams.get(teamId);
        if (team == null) {
            throw new TechCupException("Team not found with id: " + teamId);
        }
        return team;
    }

    @PostMapping("/{teamId}/players")
    @Operation(summary = "Agregar jugador a equipo", description = "Agrega un jugador al equipo indicado")
    public TeamResponse addPlayer(@PathVariable int teamId, @RequestBody AddPlayerRequest
            request) {
        Team team = getTeamOrThrow(teamId);
        teamService.addPlayerToTeam(team, request.player());
        return new TeamResponse(teamId, team);
    }
    
    @GetMapping("/{teamId}/players")
    @Operation(summary = "Obtener jugadores de equipo", description = "Obtiene la lista de jugadores del equipo indicado")
    public List<PlayerResponse> getPlayers(@PathVariable int teamId) {
        Team team = getTeamOrThrow(teamId);
        List<PlayerResponse> response = new ArrayList<>();
        for (Player player : team.getPlayers()) {
            response.add(new PlayerResponse(player.getId(), player.getName()));
        }
        return response;
    }

    @DeleteMapping("/{teamId}/players/{playerId}")
    @Operation(summary = "Eliminar jugador de equipo", description = "Elimina un jugador del equipo indicado")
    public TeamResponse removePlayer(@PathVariable int teamId, @PathVariable int playerId) {
        Team team = getTeamOrThrow(teamId);
        Player player = team.getPlayers().stream()
                .filter(p -> p.getId() == playerId)
                .findFirst()
                .orElseThrow(() -> new TechCupException("Player not found with id: " + playerId));
        team.getPlayers().remove(player);
        return new TeamResponse(teamId, team);
 
    }

    @PutMapping("/{teamId}/players/{playerId}")
    @Operation(summary = "Actualizar jugador de equipo", description = "Actualiza los datos de un jugador del equipo indicado")
    public TeamResponse updatePlayer(@PathVariable int teamId, @PathVariable int playerId, @RequestBody Player updatedPlayer) {
        Team team = getTeamOrThrow(teamId);
        Player player = team.getPlayers().stream()
                .filter(p -> p.getId() == playerId)
                .findFirst()
                .orElseThrow(() -> new TechCupException("Player not found with id: " + playerId));
        player.setName(updatedPlayer.getName());
        player.setPosition(updatedPlayer.getPosition());
        player.setAvailable(updatedPlayer.isAvailable());
        player.setTypePlayer(updatedPlayer.getTypePlayer());
        return new TeamResponse(teamId, team);
    }

    public record PlayerResponse(int id, String name) {}
}