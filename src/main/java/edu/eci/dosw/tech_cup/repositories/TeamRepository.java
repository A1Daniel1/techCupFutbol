package edu.eci.dosw.tech_cup.repositories;

import edu.eci.dosw.tech_cup.entities.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {
}
