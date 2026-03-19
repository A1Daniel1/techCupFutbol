package edu.eci.dosw.tech_cup.model;

import edu.eci.dosw.tech_cup.enums.TypeUser;

public class Visitor extends User {

    public Visitor() {}

    public Visitor(int id, String name, String email, int age) {
        super(id, name, email, age, TypeUser.GEST);
    }
}
