package edu.eci.dosw.tech_cup.model;

import edu.eci.dosw.tech_cup.enum.TypeUser;

public abstract class User {

    private int id;
    private String name;
    private String email;
    private int age;
    private TypeUser role;

    protected User() {}

    protected User(int id, String name, String email, int age, TypeUser role) {
        this.id    = id;
        this.name  = name;
        this.email = email;
        this.age   = age;
        this.role  = role;
    }

    public int getId()               { return id; }
    public void setId(int id)        { this.id = id; }

    public String getName()                { return name; }
    public void setName(String name)       { this.name = name; }

    public String getEmail()               { return email; }
    public void setEmail(String email)     { this.email = email; }

    public int getAge()                    { return age; }
    public void setAge(int age)            { this.age = age; }

    public TypeUser getRole()              { return role; }
    public void setRole(TypeUser role)     { this.role = role; }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', email='" + email
               + "', age=" + age + ", role=" + role + "}";
    }
}
