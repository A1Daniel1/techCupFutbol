package edu.eci.dosw.tech_cup.model;

import edu.eci.dosw.tech_cup.enums.TypePlayer;

public class StudentPlayer extends Player {

    private String academicProgram;

    public StudentPlayer() {}

    public StudentPlayer(int id, String name, String email, int age,
                         int number, String nickName, String position,
                         boolean available, TypePlayer typePlayer, String academicProgram) {
        super(id, name, email, age, number, nickName, position, available, typePlayer);
        this.academicProgram = academicProgram;
    }

    public String getAcademicProgram()                       { return academicProgram; }
    public void setAcademicProgram(String academicProgram)   { this.academicProgram = academicProgram; }

    @Override
    public String toString() {
        return "StudentPlayer{academicProgram='" + academicProgram + "', " + super.toString() + "}";
    }
}
