package edu.eci.dosw.tech_cup.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.dosw.tech_cup.dto.LineUp;
import edu.eci.dosw.tech_cup.model.Player;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/line-ups")
@Tag(name = "Line Ups", description = "Endpoints para gestion de line-ups de equipos")
public class LineUpController {

    @GetMapping("/{teamId}")
    @Operation(summary = "Obtener line-up de equipo", description = "Retorna el line-up del equipo indicado")
    public LineUp getTeamLineUp(@PathVariable int teamId) {
        LineUp lineUp = new LineUp();
        return lineUp;
    }

    @PostMapping("/{teamId}/players")
    @Operation(summary = "Agregar jugador al line-up", description = "Agrega un jugador al line-up del equipo indicado")
    public LineUp addPlayerToLineUp(@PathVariable int teamId, @RequestBody Player player) {
        LineUp lineUp = new LineUp();
        return lineUp;
    }

    @DeleteMapping("/{playerId}")
    @Operation(summary = "Eliminar jugador del line-up", description = "Elimina un jugador del line-up del equipo indicado")
    public LineUp removePlayerFromLineUp(@PathVariable int teamId, @PathVariable int playerId) {
        LineUp lineUp = new LineUp();
        return lineUp;
    }

    @PutMapping("/{playerId}")
    @Operation(summary = "Cambiar posición del jugador", description = "Cambia la posición de un jugador en el line-up")
    public Player updatePlayerPosition(@PathVariable int teamId, @PathVariable int playerId, @RequestBody String position) {
        Player player = new ConcretePlayer();
        player.setPosition(position);
        return player;
    }

    private static class ConcretePlayer extends Player {
        ConcretePlayer() {
            super();
        }
    }
}
