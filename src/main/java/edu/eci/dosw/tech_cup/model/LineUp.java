package edu.eci.dosw.tech_cup.model;

import java.util.ArrayList;

public class LineUp {

    private ArrayList<Player> players;

    public LineUp() {
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        if (player == null)
            throw new IllegalArgumentException("Player cannot be null");
        if (players.size() >= 11)
            throw new IllegalStateException("Lineup cannot have more than 11 players");
        if (!player.isAvailable())
            throw new IllegalStateException("Player " + player.getNickName() + " is not available");
        players.add(player);
    }

    public boolean isComplete() {
        return players.size() == 11;
    }

    public ArrayList<Player> getPlayers()       { return players; }
    public void setPlayers(ArrayList<Player> p) { this.players = p; }

    @Override
    public String toString() {
        return "LineUp{players=" + players.size() + "}";
    }
}
