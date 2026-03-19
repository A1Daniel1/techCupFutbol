package edu.eci.dosw.tech_cup.model;

import edu.eci.dosw.tech_cup.enum.TypeUser;

public class Administrator extends User {

    public Administrator() {}

    public Administrator(int id, String name, String email, int age) {
        super(id, name, email, age, TypeUser.ADMINISTRATOR);
    }
}
