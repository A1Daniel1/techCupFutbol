package edu.eci.dosw.tech_cup.repositories;

import java.util.*;
import edu.eci.dosw.tech_cup.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public List<UserEntity> findAll();

    public List<UserEntity> findByNameContainingIgnoreCase(String name);

}
