package edu.eci.dosw.tech_cup.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.eci.dosw.tech_cup.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public List<UserEntity> findAll();

    public List<UserEntity> findByNameContainingIgnoreCase(String name);

    public UserEntity findByEmail(String email);

    public UserEntity findByEmailIgnoreCase(String email);

}
