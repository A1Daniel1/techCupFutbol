package edu.eci.dosw.tech_cup.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import edu.eci.dosw.tech_cup.dto.User;
import edu.eci.dosw.tech_cup.entities.RoleEntity;
import edu.eci.dosw.tech_cup.entities.UserEntity;
import edu.eci.dosw.tech_cup.mappers.UserMapper;
import edu.eci.dosw.tech_cup.repositories.RoleRepository;
import edu.eci.dosw.tech_cup.repositories.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
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
        if (userRepository.existsByEmailIgnoreCase(user.getEmail())) {
            throw new IllegalArgumentException("User email already exists");
        }
        UserEntity entity = userMapper.toEntity(user, password, academicProgram);
        entity.setRoles(resolveRoles(user));
        UserEntity saved = userRepository.save(entity);
        return userMapper.toDto(saved);
    }

    public User updateUser(Long id, User user, String password, String academicProgram) {
        validateUserData(user, password);
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        String currentEmail = entity.getEmail();
        if ((currentEmail == null || !currentEmail.equalsIgnoreCase(user.getEmail()))
            && userRepository.existsByEmailIgnoreCase(user.getEmail())) {
            throw new IllegalArgumentException("User email already exists");
        }
        userMapper.apply(entity, user, password, academicProgram);
        entity.setRoles(resolveRoles(user));
        UserEntity saved = userRepository.save(entity);
        return userMapper.toDto(saved);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found");
        }
        userRepository.deleteById(id);
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

    private Set<RoleEntity> resolveRoles(User user) {
        Set<RoleEntity> roles = new HashSet<>();
        if (user.getRole() == null) {
            return roles;
        }
        String roleName = user.getRole().name();
        RoleEntity role = roleRepository.findByName(roleName)
                .orElseGet(() -> {
                    RoleEntity newRole = new RoleEntity();
                    newRole.setName(roleName);
                    return roleRepository.save(newRole);
                });
        roles.add(role);
        return roles;
    }

    public UserDetails loadUserByEmail(String email) {
        UserEntity user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getType().name())
                .build();
    }

}
