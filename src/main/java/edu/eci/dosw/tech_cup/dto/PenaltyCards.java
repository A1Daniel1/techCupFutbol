package edu.eci.dosw.tech_cup.dto;

public class PenaltyCards {
    private int yellowCard;
    private int redCard;

    public PenaltyCards() {
        this.yellowCard = 0;
        this.redCard = 0;
    }

    public PenaltyCards(int yellowCard, int redCard) {
        this.yellowCard = yellowCard;
        this.redCard = redCard;
    }

    public int getYellowCard() {
        return yellowCard;
    }

    public void setYellowCard(int yellowCard) {
        this.yellowCard = yellowCard;
    }

    public int getRedCard() {
        return redCard;
    }

    public void setRedCard(int redCard) {
        this.redCard = redCard;
    }

}
