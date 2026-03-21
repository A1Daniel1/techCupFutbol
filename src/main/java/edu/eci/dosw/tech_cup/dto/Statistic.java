package edu.eci.dosw.tech_cup.dto;

import java.util.ArrayList;

public class Statistic {

    private ArrayList<Player> players;
    private ArrayList<Team> teams;
    private ArrayList<Match> matches;

    public Statistic() {
        this.players = new ArrayList<>();
        this.teams = new ArrayList<>();
        this.matches = new ArrayList<>();
    }

    public ArrayList<Player> getPlayers() { return players; }
    public void setPlayers(ArrayList<Player> players) { this.players = players; }

    public ArrayList<Team> getTeams() { return teams; }
    public void setTeams(ArrayList<Team> teams) { this.teams = teams; }

    public ArrayList<Match> getMatches() { return matches; }
    public void setMatches(ArrayList<Match> matches) { this.matches = matches; }

}
