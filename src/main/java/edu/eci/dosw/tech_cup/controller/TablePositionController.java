package edu.eci.dosw.tech_cup.controller;

import edu.eci.dosw.tech_cup.dto.Team;
import edu.eci.dosw.tech_cup.services.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
    

@RestController
@RequestMapping("/table-positions")
@Tag(name = "Table Positions", description = "Endpoints para gestion de posiciones en la tabla de posiciones")
public class TablePositionController {

    private final Map<Integer, Team> teams = new ConcurrentHashMap<>();

    public TablePositionController(TeamService teamService) {
    }

    @GetMapping
    @Operation(summary = "Listar posiciones en tabla", description = "Retorna las posiciones de los equipos en la tabla de posiciones ordenados por puntos")
    public List<TablePositionEntry> getTablePositions() {
        return teams.entrySet().stream()
                .map(entry -> new TablePositionEntry(entry.getKey(), entry.getValue().getName(), entry.getValue().getPoints()))
                .sorted(Comparator.comparingInt(TablePositionEntry::points).reversed())
                .collect(Collectors.toList());
    }

    @GetMapping("/{teamId}")
    @Operation(summary = "Obtener posición de equipo", description = "Retorna la posición del equipo indicado en la tabla de posiciones")
    public int getTeamPosition(@PathVariable int teamId) {
        var sortedEntries = teams.entrySet().stream()
                .sorted((a, b) -> Integer.compare(b.getValue().getPoints(), a.getValue().getPoints()))
                .collect(Collectors.toList());
        for (int i = 0; i < sortedEntries.size(); i++) {
            if (sortedEntries.get(i).getKey() == teamId) {
                return i + 1;
            }
        }
        return 0;
     }
    public static record TablePositionEntry(int id, String name, int points) {
    }



    
}
