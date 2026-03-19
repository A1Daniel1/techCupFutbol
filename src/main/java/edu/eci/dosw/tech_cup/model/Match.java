package edu.eci.dosw.tech_cup.model;

import java.time.LocalTime;
import java.util.ArrayList;

public class Match {

    private LocalTime startTime;
    private LocalTime endTime;
    private Team localTeam;
    private int goals;
    private int faults;
    private int ExtraTime;
    private ArrayList<PenaltyCards> penaltyCards;

    public Match() {
        this.penaltyCards = new ArrayList<>();
    }

    public Match(LocalTime startTime, LocalTime endTime, Team localTeam) {
        if (startTime == null) throw new IllegalArgumentException("Start time cannot be null");
        if (localTeam == null) throw new IllegalArgumentException("Local team cannot be null");
        this.startTime    = startTime;
        this.endTime      = endTime;
        this.localTeam    = localTeam;
        this.goals        = 0;
        this.faults       = 0;
        this.ExtraTime  = 0;
        this.penaltyCards = new ArrayList<>();
    }

    public void addPenaltyCards(PenaltyCards cards) {
        if (cards == null) throw new IllegalArgumentException("PenaltyCards cannot be null");
        penaltyCards.add(cards);
    }

    public int getTotalYellowCards() {
        return penaltyCards.stream().mapToInt(PenaltyCards::getYellowCard).sum();
    }

    public int getTotalRedCards() {
        return penaltyCards.stream().mapToInt(PenaltyCards::getRedCard).sum();
    }

    public LocalTime getStartTime()                { return startTime; }
    public void setStartTime(LocalTime startTime)  { this.startTime = startTime; }

    public LocalTime getEndTime()                  { return endTime; }
    public void setEndTime(LocalTime endTime)       { this.endTime = endTime; }

    public Team getLocalTeam()                     { return localTeam; }
    public void setLocalTeam(Team localTeam)       { this.localTeam = localTeam; }

    public int getGoals()                          { return goals; }
    public void setGoals(int goals)                { this.goals = goals; }

    public int getFaults()                         { return faults; }
    public void setFaults(int faults)              { this.faults = faults; }

    public int getExtraTime()                    { return ExtraTime; }
    public void setExtraTime(int ExtraTime)    { this.ExtraTime = ExtraTime; }

    public ArrayList<PenaltyCards> getPenaltyCards()            { return penaltyCards; }
    public void setPenaltyCards(ArrayList<PenaltyCards> cards)  { this.penaltyCards = cards; }

    @Override
    public String toString() {
        return "Match{startTime=" + startTime + ", localTeam=" + localTeam
               + ", goals=" + goals + ", faults=" + faults + "}";
    }
}