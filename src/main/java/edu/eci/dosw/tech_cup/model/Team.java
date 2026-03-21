package edu.eci.dosw.tech_cup.model;

import java.util.ArrayList;

public class Team {

    private String name;
    private ArrayList<Player> players;
    private Player captain;
    private int matches;
    private int fairPlay;

    public Team() {
        this.players = new ArrayList<>();
    }

    public Team(String name) {
        this.name     = name;
        this.players  = new ArrayList<>();
        this.matches  = 0;
        this.fairPlay = 0;
    }

    public void addPlayer(Player player) {
        if (player == null) throw new IllegalArgumentException("Player cannot be null");
        if (players.size() >= 20) throw new IllegalStateException("Team roster is full (max 20 players)");
        players.add(player);
    }

    public boolean removePlayer(Player player) {
        return players.remove(player);
    }

    public void setCaptain(Player player) {
        if (!players.contains(player))
            throw new IllegalArgumentException("Captain must be a member of the team");
        this.captain = player;
    }

    public String getName()                     { return name; }
    public void setName(String name)            { this.name = name; }

    public ArrayList<Player> getPlayers()       { return players; }
    public void setPlayers(ArrayList<Player> p) { this.players = p; }

    public Player getCaptain()                  { return captain; }

    public int getMatches()                     { return matches; }
    public void setMatches(int matches)         { this.matches = matches; }

    public int getFairPlay()                    { return fairPlay; }
    public void setFairPlay(int fairPlay)       { this.fairPlay = fairPlay; }

    @Override
    public String toString() {
        return "Team{name='" + name + "', players=" + players.size()
               + ", matches=" + matches + ", fairPlay=" + fairPlay + "}";
    }
}
