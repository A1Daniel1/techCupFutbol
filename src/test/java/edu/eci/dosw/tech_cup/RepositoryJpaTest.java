package edu.eci.dosw.tech_cup;

import edu.eci.dosw.tech_cup.entities.MatchEntity;
import edu.eci.dosw.tech_cup.entities.TournamentEntity;
import edu.eci.dosw.tech_cup.entities.UserEntity;
import edu.eci.dosw.tech_cup.enums.TypeUser;
import edu.eci.dosw.tech_cup.repositories.MatchRepository;
import edu.eci.dosw.tech_cup.repositories.TournamentRepository;
import edu.eci.dosw.tech_cup.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class RepositoryJpaTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Test
    @DisplayName("Debe guardar un usuario correctamente")
    void shouldSaveUser() {
        UserEntity user = buildUser("Ana Rodriguez", "ana@eci.edu.co");

        UserEntity saved = userRepository.save(user);

        assertNotNull(saved.getId());
        assertEquals("Ana Rodriguez", saved.getName());
        assertEquals("ana@eci.edu.co", saved.getEmail());
    }

    @Test
    @DisplayName("Debe consultar usuarios por nombre ignorando mayusculas")
    void shouldFindUsersByNameIgnoringCase() {
        userRepository.save(buildUser("Carlos Perez", "carlos@eci.edu.co"));
        userRepository.save(buildUser("carlota Mejia", "carlota@eci.edu.co"));

        List<UserEntity> found = userRepository.findByNameContainingIgnoreCase("carl");

        assertEquals(2, found.size());
    }

    @Test
    @DisplayName("Debe persistir relacion torneo-partido")
    void shouldPersistTournamentMatchRelationship() {
        TournamentEntity tournament = new TournamentEntity();
        tournament.setPayload("{\"name\":\"Copa ECI\"}");
        TournamentEntity savedTournament = tournamentRepository.save(tournament);

        MatchEntity match = new MatchEntity();
        match.setPayload("{\"goals\":2}");
        match.setTournament(savedTournament);
        MatchEntity savedMatch = matchRepository.save(match);

        Optional<MatchEntity> reloadedMatch = matchRepository.findById(savedMatch.getId());

        assertTrue(reloadedMatch.isPresent());
        assertNotNull(reloadedMatch.get().getTournament());
        assertEquals(savedTournament.getId(), reloadedMatch.get().getTournament().getId());
    }

    @Test
    @DisplayName("Debe eliminar usuario por id")
    void shouldDeleteUserById() {
        UserEntity saved = userRepository.save(buildUser("Laura Diaz", "laura@eci.edu.co"));

        userRepository.deleteById(saved.getId());

        assertFalse(userRepository.findById(saved.getId()).isPresent());
    }

    private UserEntity buildUser(String name, String email) {
        UserEntity user = new UserEntity();
        user.setName(name);
        user.setEmail(email);
        user.setAge(21);
        user.setType(TypeUser.PLAYER);
        user.setPassword("secret");
        user.setAcademicProgram("Ingenieria de Sistemas");
        return user;
    }
}