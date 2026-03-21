package edu.eci.dosw.tech_cup.dto;

import java.util.ArrayList;

public class Group {

    private String name;
    private ArrayList<Team> teams;
    private ArrayList<Match> matches;

    public Group() {
        this.teams = new ArrayList<>();
        this.matches = new ArrayList<>();
    }

    public Group(String name) {
        this.name = name;
        this.teams = new ArrayList<>();
        this.matches = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<Match> matches) {
        this.matches = matches;
    }

    public void addTeam(Team team) {
        if (team == null)
            throw new IllegalArgumentException("Team cannot be null");
        teams.add(team);
    }

    public void addMatch(Match match) {
        if (match == null)
            throw new IllegalArgumentException("Match cannot be null");
        matches.add(match);
    }

}
