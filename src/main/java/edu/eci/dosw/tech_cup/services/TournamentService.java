package edu.eci.dosw.tech_cup.services;

import edu.eci.dosw.tech_cup.exception.TechCupException;
import edu.eci.dosw.tech_cup.model.Match;
import edu.eci.dosw.tech_cup.model.Referee;
import edu.eci.dosw.tech_cup.model.Team;
import edu.eci.dosw.tech_cup.model.Tournament;

import java.util.ArrayList;
import java.util.List;

public class TournamentService {

    private final List<Tournament> tournaments = new ArrayList<>();

    public void createTournament(Tournament tournament) {
        if (tournament == null) throw new TechCupException(TechCupException.TOURNAMENT_NULL);
        tournaments.add(tournament);
    }

    public void registerTeam(Tournament tournament, Team team) {
        if (tournament == null) throw new TechCupException(TechCupException.TOURNAMENT_NULL);
        if (team == null)       throw new TechCupException(TechCupException.TEAM_NULL);
        if (team.getPlayers().size() < 7)
            throw new TechCupException(TechCupException.TEAM_MIN_PLAYERS);
        tournament.registerTeam(team);
    }

    public void scheduleMatch(Tournament tournament, Match match) {
        if (tournament == null) throw new TechCupException(TechCupException.TOURNAMENT_NULL);
        if (match == null)      throw new TechCupException(TechCupException.MATCH_NULL);
        tournament.scheduleMatch(match);
    }

    public void assignReferee(Tournament tournament, Referee referee) {
        if (tournament == null) throw new TechCupException(TechCupException.TOURNAMENT_NULL);
        if (referee == null)    throw new TechCupException(TechCupException.REFEREE_NULL);
        tournament.setReferee(referee);
    }

    public List<Tournament> getAllTournaments() { return tournaments; }
}
