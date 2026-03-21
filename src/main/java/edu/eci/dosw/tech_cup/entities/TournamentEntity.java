package edu.eci.dosw.tech_cup.entities;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "tournaments")
public class TournamentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "tournament")
    private List<UserEntity> users;

    @OneToMany(mappedBy = "tournament")
    private List<MatchEntity> matches;

    public TournamentEntity() {
    }

    public TournamentEntity(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
