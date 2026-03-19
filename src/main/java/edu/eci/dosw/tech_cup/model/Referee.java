package edu.eci.dosw.tech_cup.model;

import edu.eci.dosw.tech_cup.enum.TypeUser;

public class Referee extends User {

    private String refereeType;

    public Referee() {}

    public Referee(int id, String name, String email, int age, String refereeType) {
        super(id, name, email, age, TypeUser.REFEREE);
        this.refereeType = refereeType;
    }

    public String getRefereeType()                   { return refereeType; }
    public void setRefereeType(String refereeType)   { this.refereeType = refereeType; }

    @Override
    public String toString() {
        return "Referee{refereeType='" + refereeType + "', " + super.toString() + "}";
    }
}
