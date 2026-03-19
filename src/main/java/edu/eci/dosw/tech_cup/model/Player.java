package edu.eci.dosw.tech_cup.model;

import edu.eci.dosw.tech_cup.enums.TypePlayer;
import edu.eci.dosw.tech_cup.enums.TypeUser;

public abstract class Player extends User {

    private int number;
    private String nickName;
    private String position;
    private boolean available;
    private TypePlayer typePlayer;

    protected Player() {}

    protected Player(int id, String name, String email, int age,
                     int number, String nickName, String position,
                     boolean available, TypePlayer typePlayer) {
        super(id, name, email, age, TypeUser.PLAYER);
        this.number     = number;
        this.nickName   = nickName;
        this.position   = position;
        this.available  = available;
        this.typePlayer = typePlayer;
    }

    public int getNumber()                        { return number; }
    public void setNumber(int number)             { this.number = number; }

    public String getNickName()                   { return nickName; }
    public void setNickName(String nickName)      { this.nickName = nickName; }

    public String getPosition()                   { return position; }
    public void setPosition(String position)      { this.position = position; }

    public boolean isAvailable()                  { return available; }
    public void setAvailable(boolean available)   { this.available = available; }

    public TypePlayer getTypePlayer()             { return typePlayer; }
    public void setTypePlayer(TypePlayer tp)      { this.typePlayer = tp; }

    @Override
    public String toString() {
        return "Player{number=" + number + ", nickName='" + nickName
               + "', available=" + available + ", typePlayer=" + typePlayer
               + ", " + super.toString() + "}";
    }
}
