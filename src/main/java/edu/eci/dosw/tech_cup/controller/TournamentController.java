package edu.eci.dosw.tech_cup.controller;

import edu.eci.dosw.tech_cup.model.Match;
import edu.eci.dosw.tech_cup.model.Referee;
import edu.eci.dosw.tech_cup.model.Team;
import edu.eci.dosw.tech_cup.model.Tournament;
import edu.eci.dosw.tech_cup.services.TournamentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {

    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping
    public List<Tournament> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }

    @PostMapping
    public Tournament createTournament(@RequestBody Tournament tournament) {
        tournamentService.createTournament(tournament);
        return tournament;
    }

    @PostMapping("/{tournamentIndex}/teams")
    public Tournament registerTeam(@PathVariable int tournamentIndex, @RequestBody Team team) {
        Tournament tournament = getTournamentByIndex(tournamentIndex);
        tournamentService.registerTeam(tournament, team);
        return tournament;
    }

    @PostMapping("/{tournamentIndex}/matches")
    public Tournament scheduleMatch(@PathVariable int tournamentIndex, @RequestBody Match match) {
        Tournament tournament = getTournamentByIndex(tournamentIndex);
        tournamentService.scheduleMatch(tournament, match);
        return tournament;
    }

    @PostMapping("/{tournamentIndex}/referee")
    public Tournament assignReferee(@PathVariable int tournamentIndex, @RequestBody Referee referee) {
        Tournament tournament = getTournamentByIndex(tournamentIndex);
        tournamentService.assignReferee(tournament, referee);
        return tournament;
    }

    private Tournament getTournamentByIndex(int index) {
        return tournamentService.getAllTournaments().get(index);
    }
}
