package edu.eci.dosw.tech_cup.repositories;

import edu.eci.dosw.tech_cup.entities.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<MatchEntity, Long> {
}
