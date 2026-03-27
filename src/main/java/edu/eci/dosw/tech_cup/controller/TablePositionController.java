package edu.eci.dosw.tech_cup.controller;

import edu.eci.dosw.tech_cup.dto.Team;
import edu.eci.dosw.tech_cup.services.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import ch.qos.logback.core.net.server.ServerListener;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/table-positions")
@Tag(name = "Table Positions", description = "Endpoints para gestion de posiciones en la tabla de posiciones")
public class TablePositionController {

    private final Map<Integer, Team> teams = new ConcurrentHashMap<>();
    private final TeamService teamService;

    public TablePositionController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    @Operation(summary = "Listar posiciones en tabla",
               description = "Retorna las posiciones de los equipos en la tabla de posiciones ordenados por puntos")
    public List<TablePositionEntry> getTablePositions() {
        //servicio
        return Service;
    }

    @GetMapping("/{teamId}")
    @Operation(summary = "Obtener posición de equipo",
               description = "Retorna la posición del equipo indicado en la tabla de posiciones")
    public int getTeamPosition(@PathVariable int teamId) {
        //servicio
        return Server;
    }
}