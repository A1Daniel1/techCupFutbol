package edu.eci.dosw.tech_cup.model;

import java.util.ArrayList;

public class Statistic {

    private ArrayList<Player> players;
    private ArrayList<Team>   teams;
    private ArrayList<Match>  matches;

    public Statistic() {
        this.players = new ArrayList<>();
        this.teams   = new ArrayList<>();
        this.matches = new ArrayList<>();
    }

    public Team getBestFairPlayTeam() {
        return teams.stream()
                .max((t1, t2) -> Integer.compare(t1.getFairPlay(), t2.getFairPlay()))
                .orElse(null);
    }

    public int getTotalMatches() { return matches.size(); }

    public ArrayList<Player> getPlayers()       { return players; }
    public void setPlayers(ArrayList<Player> p) { this.players = p; }

    public ArrayList<Team> getTeams()           { return teams; }
    public void setTeams(ArrayList<Team> t)     { this.teams = t; }

    public ArrayList<Match> getMatches()        { return matches; }
    public void setMatches(ArrayList<Match> m)  { this.matches = m; }

    @Override
    public String toString() {
        return "Statistic{players=" + players.size()
               + ", teams=" + teams.size()
               + ", matches=" + matches.size() + "}";
    }
}
