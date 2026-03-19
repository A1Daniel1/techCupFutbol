package edu.eci.dosw.tech_cup.model;

import edu.eci.dosw.tech_cup.enum.TypeUser;

public class Graduate extends User {

    private String academicProgram;

    public Graduate() {}

    public Graduate(int id, String name, String email, int age, String academicProgram) {
        super(id, name, email, age, TypeUser.PLAYER);
        this.academicProgram = academicProgram;
    }

    public String getAcademicProgram()                       { return academicProgram; }
    public void setAcademicProgram(String academicProgram)   { this.academicProgram = academicProgram; }
}
