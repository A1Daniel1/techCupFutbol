package edu.eci.dosw.tech_cup.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.eci.dosw.tech_cup.dto.User;
import edu.eci.dosw.tech_cup.entities.UserEntity;
import edu.eci.dosw.tech_cup.mappers.UserMapper;
import edu.eci.dosw.tech_cup.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        for (UserEntity entity : userRepository.findAll()) {
            users.add(userMapper.toDto(entity));
        }
        return users;
    }

    public User getUserById(Long id) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return userMapper.toDto(entity);
    }

    public User createUser(User user, String password, String academicProgram) {
        validateUserData(user, password);
        UserEntity entity = userMapper.toEntity(user, passwordEncoder.encode(password), academicProgram);
        UserEntity saved = userRepository.save(entity);
        return userMapper.toDto(saved);
    }

    public User updateUser(Long id, User user, String password, String academicProgram) {
        validateUserData(user, password);
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        userMapper.apply(entity, user, passwordEncoder.encode(password), academicProgram);
        UserEntity saved = userRepository.save(entity);
        return userMapper.toDto(saved);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found");
        }
        userRepository.deleteById(id);
    }


    public UserDetails loadUserByEmail(String email) {
        /*
         * Objetivo de loadUserByEmail: obtener el usuario por correo para autenticacion.
         * UserDetails: representa credenciales y autoridades que Spring Security valida.
         * SimpleGrantedAuthority: representa el rol/permiso concreto del usuario.
         */
        UserEntity user = userRepository.findByEmailIgnoreCase(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getType().name())));
    }

    private void validateUserData(User user, String password) {
        if (user == null) {
            throw new IllegalArgumentException("User payload is required");
        }
        if (user.getName() == null || user.getName().isBlank()) {
            throw new IllegalArgumentException("User name is required");
        }
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new IllegalArgumentException("User email is required");
        }
        if (user.getAge() <= 0) {
            throw new IllegalArgumentException("User age must be greater than zero");
        }
        if (user.getRole() == null) {
            throw new IllegalArgumentException("User role is required");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("User password is required");
        }
    }
}
