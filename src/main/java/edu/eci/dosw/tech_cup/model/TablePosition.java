package edu.eci.dosw.tech_cup.model;

import java.util.ArrayList;

public class TablePosition {

    private ArrayList<Team> teams;

    public TablePosition() {
        this.teams = new ArrayList<>();
    }

    public void addTeam(Team team) {
        if (team == null) throw new IllegalArgumentException("Team cannot be null");
        teams.add(team);
    }
    
    public int getPosition(Team team) {
        int index = teams.indexOf(team);
        return index == -1 ? -1 : index + 1;
    }

    public Team getTeamAtPosition(int position) {
        if (position < 1 || position > teams.size())
            throw new IllegalArgumentException("Invalid position: " + position);
        return teams.get(position - 1);
    }

    public ArrayList<Team> getTeams()           { return teams; }
    public void setTeams(ArrayList<Team> teams) { this.teams = teams; }

    @Override
    public String toString() {
        return "TablePosition{teams=" + teams.size() + "}";
    }
}
