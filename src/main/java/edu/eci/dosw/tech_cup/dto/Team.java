package edu.eci.dosw.tech_cup.dto;

import java.util.*;
import java.awt.*;

public class Team {

    private String name;
    private ArrayList<Player> players;
    private Image iconTeam;
    private Player captain;
    private int matches;
    private int fairPlay;
    private int losses;
    private int wins;
    private int draws;
    private int points;
    private int goalsFor;
    private int goalsAgainst;
    private int goalDifference;
    private Group group;

    public Team() {
        this.players = new ArrayList<>();
    }

    public Team(String name) {
        this.name = name;
        this.players = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public Player getCaptain() {
        return captain;
    }

    public void setCaptain(Player captain) {
        this.captain = captain;
    }

    public Image getIconTeam() {
        return iconTeam;
    }

    public void setIconTeam(Image iconTeam) {
        this.iconTeam = iconTeam;
    }

    public int getMatches() {
        return matches;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public int getFairPlay() {
        return fairPlay;
    }

    public void setFairPlay(int fairPlay) {
        this.fairPlay = fairPlay;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

}
