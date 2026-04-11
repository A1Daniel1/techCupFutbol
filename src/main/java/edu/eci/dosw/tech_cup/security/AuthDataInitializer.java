package edu.eci.dosw.tech_cup.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.eci.dosw.tech_cup.entities.UserEntity;
import edu.eci.dosw.tech_cup.enums.TypeUser;
import edu.eci.dosw.tech_cup.repositories.UserRepository;

@Configuration
public class AuthDataInitializer {

    private static final String DEFAULT_EMAIL = "usuario@eci.edu.co";
    private static final String DEFAULT_PASSWORD = "correcta";

    @Bean
    @Profile("!test")
    public CommandLineRunner seedAuthUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            UserEntity existing = userRepository.findByEmailIgnoreCase(DEFAULT_EMAIL);

            if (existing == null) {
                UserEntity user = new UserEntity();
                user.setName("Usuario Demo");
                user.setEmail(DEFAULT_EMAIL);
                user.setAge(20);
                user.setType(TypeUser.PLAYER);
                user.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
                user.setAcademicProgram("Ingenieria de Sistemas");
                userRepository.save(user);
                return;
            }

            boolean updated = false;

            if (existing.getType() == null) {
                existing.setType(TypeUser.PLAYER);
                updated = true;
            }

            if (existing.getAge() <= 0) {
                existing.setAge(20);
                updated = true;
            }

            if (!hasDefaultPassword(existing.getPassword(), passwordEncoder)) {
                existing.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
                updated = true;
            }

            if (updated) {
                userRepository.save(existing);
            }
        };
    }

    private boolean hasDefaultPassword(String currentPassword, PasswordEncoder passwordEncoder) {
        if (currentPassword == null || currentPassword.isBlank()) {
            return false;
        }

        if (isBcryptHash(currentPassword)) {
            return passwordEncoder.matches(DEFAULT_PASSWORD, currentPassword);
        }

        return DEFAULT_PASSWORD.equals(currentPassword);
    }

    private boolean isBcryptHash(String value) {
        return value != null
                && (value.startsWith("$2a$") || value.startsWith("$2b$") || value.startsWith("$2y$"));
    }
}
