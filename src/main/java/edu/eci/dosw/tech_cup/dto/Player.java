package edu.eci.dosw.tech_cup.dto;

import edu.eci.dosw.tech_cup.enums.TypePlayer;

public class Player {

    private int number;
    private String nickName;
    private String position;
    private boolean available;
    private TypePlayer typePlayer;

    public Player() {
        super();
    }

    public Player(int number, String nickName, String position, boolean available, TypePlayer typePlayer) {
        this.number = number;
        this.nickName = nickName;
        this.position = position;
        this.available = available;
        this.typePlayer = typePlayer;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public TypePlayer getTypePlayer() {
        return typePlayer;
    }

    public void setTypePlayer(TypePlayer typePlayer) {
        this.typePlayer = typePlayer;
    }

}
