package edu.eci.dosw.tech_cup.dto;

import edu.eci.dosw.tech_cup.enums.TypeUser;

import java.util.*;

public class User {

    private int id;
    private String name;
    private String email;
    private int age;
    private TypeUser role;
    private ArrayList<Tournament> tournaments;

    public User() {

    }

    public User(int id, String name, String email, int age, TypeUser role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.role = role;
        this.tournaments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(TypeUser role) {
        this.role = role;
    }

    public TypeUser getRole() {
        return role;
    }

    public ArrayList<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(ArrayList<Tournament> tournaments) {
        this.tournaments = tournaments;
    }
}
