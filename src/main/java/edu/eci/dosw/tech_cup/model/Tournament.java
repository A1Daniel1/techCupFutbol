package edu.eci.dosw.tech_cup.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Tournament {

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private ArrayList<User>   users;
    private ArrayList<Team>   teams;
    private ArrayList<Match>  matches;
    private Referee           referee;
    private TablePosition     tablePosition;
    private Statistic         statistic;

    public Tournament() {
        this.users   = new ArrayList<>();
        this.teams   = new ArrayList<>();
        this.matches = new ArrayList<>();
    }

    public Tournament(LocalDate startDate, LocalDate endDate,
                      LocalTime startTime, LocalTime endTime) {
        if (startDate == null) throw new IllegalArgumentException("Start date cannot be null");
        if (endDate   == null) throw new IllegalArgumentException("End date cannot be null");
        if (endDate.isBefore(startDate))
            throw new IllegalArgumentException("End date cannot be before start date");
        this.startDate = startDate;
        this.endDate   = endDate;
        this.startTime = startTime;
        this.endTime   = endTime;
        this.users     = new ArrayList<>();
        this.teams     = new ArrayList<>();
        this.matches   = new ArrayList<>();
    }

    public void registerTeam(Team team) {
        if (team == null) throw new IllegalArgumentException("Team cannot be null");
        if (teams.contains(team)) throw new IllegalArgumentException("Team already registered");
        teams.add(team);
    }

    public void scheduleMatch(Match match) {
        if (match == null) throw new IllegalArgumentException("Match cannot be null");
        matches.add(match);
    }

    public void registerUser(User user) {
        if (user == null) throw new IllegalArgumentException("User cannot be null");
        users.add(user);
    }

    public boolean isActive() {
        LocalDate today = LocalDate.now();
        return !today.isBefore(startDate) && !today.isAfter(endDate);
    }

    public LocalDate getStartDate()                         { return startDate; }
    public void setStartDate(LocalDate startDate)           { this.startDate = startDate; }

    public LocalDate getEndDate()                           { return endDate; }
    public void setEndDate(LocalDate endDate)               { this.endDate = endDate; }

    public LocalTime getStartTime()                         { return startTime; }
    public void setStartTime(LocalTime startTime)           { this.startTime = startTime; }

    public LocalTime getEndTime()                           { return endTime; }
    public void setEndTime(LocalTime endTime)               { this.endTime = endTime; }

    public ArrayList<User> getUsers()                       { return users; }
    public void setUsers(ArrayList<User> users)             { this.users = users; }

    public ArrayList<Team> getTeams()                       { return teams; }
    public void setTeams(ArrayList<Team> teams)             { this.teams = teams; }

    public ArrayList<Match> getMatches()                    { return matches; }
    public void setMatches(ArrayList<Match> matches)        { this.matches = matches; }

    public Referee getReferee()                             { return referee; }
    public void setReferee(Referee referee)                 { this.referee = referee; }

    public TablePosition getTablePosition()                 { return tablePosition; }
    public void setTablePosition(TablePosition tp)          { this.tablePosition = tp; }

    public Statistic getStatistic()                         { return statistic; }
    public void setStatistic(Statistic statistic)           { this.statistic = statistic; }

    @Override
    public String toString() {
        return "Tournament{startDate=" + startDate + ", endDate=" + endDate
               + ", teams=" + teams.size() + ", matches=" + matches.size() + "}";
    }
}
