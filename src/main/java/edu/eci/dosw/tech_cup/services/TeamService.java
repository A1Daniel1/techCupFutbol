package edu.eci.dosw.tech_cup.services;

import edu.eci.dosw.tech_cup.exception.TechCupException;
import edu.eci.dosw.tech_cup.model.Player;
import edu.eci.dosw.tech_cup.model.Team;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    public Team createTeam(String name) {
        if (name == null || name.isBlank())
            throw new TechCupException(TechCupException.TEAM_NAME_EMPTY);
        return new Team(name);
    }

    public void addPlayerToTeam(Team team, Player player) {
        if (team   == null) throw new TechCupException(TechCupException.TEAM_NULL);
        if (player == null) throw new TechCupException(TechCupException.PLAYER_NULL);
        if (team.getPlayers().contains(player))
            throw new TechCupException(TechCupException.PLAYER_DUPLICATE);
        team.addPlayer(player);
    }

    public void setCaptain(Team team, Player captain) {
        if (team    == null) throw new TechCupException(TechCupException.TEAM_NULL);
        if (captain == null) throw new TechCupException(TechCupException.CAPTAIN_NULL);
        team.setCaptain(captain);
    }
}
