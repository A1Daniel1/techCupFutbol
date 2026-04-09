package edu.eci.dosw.tech_cup.dto;

import java.time.LocalTime;
import java.util.ArrayList;

public class Match {

    private LocalTime startTime;
    private LocalTime endTime;
    private Team localTeam;
    private Team awayTeam;
    private int goals;
    private int faults;
    private int extraTime;
    private ArrayList<PenaltyCards> penaltyCards;
    private Group group;

    public Match() {
        this.penaltyCards = new ArrayList<>();
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Team getLocalTeam() {
        return localTeam;
    }

    public void setLocalTeam(Team localTeam) {
        this.localTeam = localTeam;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getFaults() {
        return faults;
    }

    public void setFaults(int faults) {
        this.faults = faults;
    }

    public int getExtraTime() {
        return extraTime;
    }

    public void setExtraTime(int extraTime) {
        this.extraTime = extraTime;
    }

    public ArrayList<PenaltyCards> getPenaltyCards() {
        return penaltyCards;
    }

    public void setPenaltyCards(ArrayList<PenaltyCards> penaltyCards) {
        this.penaltyCards = penaltyCards;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

}
