package edu.eci.dosw.tech_cup.model;

import edu.eci.dosw.tech_cup.enum.TypeUser;

public class Professor extends User {

    public Professor() {}

    public Professor(int id, String name, String email, int age) {
        super(id, name, email, age, TypeUser.GEST);
    }
}
