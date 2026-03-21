package edu.eci.dosw.tech_cup.model;

public class PenaltyCards {

    private int yellowCard;
    private int redCard;

    public PenaltyCards() {}

    public PenaltyCards(int yellowCard, int redCard) {
        if (yellowCard < 0) throw new IllegalArgumentException("Yellow cards cannot be negative");
        if (redCard    < 0) throw new IllegalArgumentException("Red cards cannot be negative");
        this.yellowCard = yellowCard;
        this.redCard    = redCard;
    }

    public void addYellowCard() { this.yellowCard++; }
    public void addRedCard()    { this.redCard++; }

    public int getYellowCard()               { return yellowCard; }
    public void setYellowCard(int yellowCard) {
        if (yellowCard < 0) throw new IllegalArgumentException("Yellow cards cannot be negative");
        this.yellowCard = yellowCard;
    }

    public int getRedCard()                  { return redCard; }
    public void setRedCard(int redCard) {
        if (redCard < 0) throw new IllegalArgumentException("Red cards cannot be negative");
        this.redCard = redCard;
    }

    @Override
    public String toString() {
        return "PenaltyCards{yellowCard=" + yellowCard + ", redCard=" + redCard + "}";
    }
}
