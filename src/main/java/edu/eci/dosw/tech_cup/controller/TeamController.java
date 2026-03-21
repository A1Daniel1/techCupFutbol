package edu.eci.dosw.tech_cup.controller;

import edu.eci.dosw.tech_cup.exception.TechCupException;
import edu.eci.dosw.tech_cup.model.Player;
import edu.eci.dosw.tech_cup.model.Team;
import edu.eci.dosw.tech_cup.services.TeamService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;
    private final Map<Integer, Team> teams = new ConcurrentHashMap<>();
    private final AtomicInteger sequence = new AtomicInteger(1);

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<TeamResponse> getTeams() {
        List<TeamResponse> response = new ArrayList<>();
        for (Map.Entry<Integer, Team> entry : teams.entrySet()) {
            response.add(new TeamResponse(entry.getKey(), entry.getValue()));
        }
        return response;
    }

    @PostMapping
    public TeamResponse createTeam(@RequestBody CreateTeamRequest request) {
        Team createdTeam = teamService.createTeam(request.name());
        int id = sequence.getAndIncrement();
        teams.put(id, createdTeam);
        return new TeamResponse(id, createdTeam);
    }

    @PostMapping("/{teamId}/players")
    public TeamResponse addPlayer(@PathVariable int teamId, @RequestBody AddPlayerRequest request) {
        Team team = getTeamOrThrow(teamId);
        teamService.addPlayerToTeam(team, request.player());
        return new TeamResponse(teamId, team);
    }

    @PostMapping("/{teamId}/captain")
    public TeamResponse setCaptain(@PathVariable int teamId, @RequestBody SetCaptainRequest request) {
        Team team = getTeamOrThrow(teamId);
        int index = request.playerIndex();
        if (index < 0 || index >= team.getPlayers().size()) {
            throw new IllegalArgumentException("Captain index out of bounds");
        }
        Player captain = team.getPlayers().get(index);
        teamService.setCaptain(team, captain);
        return new TeamResponse(teamId, team);
    }

    private Team getTeamOrThrow(int teamId) {
        Team team = teams.get(teamId);
        if (team == null) {
            throw new TechCupException(TechCupException.TEAM_NULL);
        }
        return team;
    }

    public record CreateTeamRequest(String name) {}

    public record AddPlayerRequest(Player player) {}

    public record SetCaptainRequest(int playerIndex) {}

    public record TeamResponse(int id, Team team) {}
}
