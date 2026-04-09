package edu.eci.dosw.tech_cup.model;

import edu.eci.dosw.tech_cup.enums.TypePlayer;

public class GraduatePlayer extends Player {

    private String academicProgram;

    public GraduatePlayer() {}

    public GraduatePlayer(int id, String name, String email, int age,
                          int number, String nickName, String position,
                          boolean available, TypePlayer typePlayer, String academicProgram) {
        super(id, name, email, age, number, nickName, position, available, typePlayer);
        this.academicProgram = academicProgram;
    }

    public String getAcademicProgram()                       { return academicProgram; }
    public void setAcademicProgram(String academicProgram)   { this.academicProgram = academicProgram; }

    @Override
    public String toString() {
        return "GraduatePlayer{academicProgram='" + academicProgram + "', " + super.toString() + "}";
    }
}
