package edu.eci.dosw.tech_cup.services;

import java.util.ArrayList;
import java.util.List;
import edu.eci.dosw.tech_cup.dto.User;
import edu.eci.dosw.tech_cup.entities.UserEntity;
import edu.eci.dosw.tech_cup.entities.UserTypeEntity;
import edu.eci.dosw.tech_cup.enums.TypeUser;
import edu.eci.dosw.tech_cup.repositories.UserRepository;
import edu.eci.dosw.tech_cup.repositories.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTypeRepository userTypeRepository;

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        for (UserEntity entity : userRepository.findAll()) {
            users.add(toDto(entity));
        }
        return users;
    }

    public User getUserById(Long id) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return toDto(entity);
    }

    public User createUser(User user, String password, String academicProgram) {
        UserEntity entity = new UserEntity();
        applyUserData(entity, user, password, academicProgram);
        UserEntity saved = userRepository.save(entity);
        return toDto(saved);
    }

    public User updateUser(Long id, User user, String password, String academicProgram) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        applyUserData(entity, user, password, academicProgram);
        UserEntity saved = userRepository.save(entity);
        return toDto(saved);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found");
        }
        userRepository.deleteById(id);
    }

    private void applyUserData(UserEntity entity, User user, String password, String academicProgram) {
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

        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setAge(user.getAge());
        entity.setRole(resolveUserRole(user.getRole()));
        entity.setPassword(password);
        entity.setAcademicProgram(academicProgram);
    }

    private User toDto(UserEntity entity) {
        User dto = new User();
        dto.setId(entity.getId().intValue());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setAge(entity.getAge());
        if (entity.getRole() != null && entity.getRole().getName() != null) {
            dto.setRole(TypeUser.valueOf(entity.getRole().getName()));
        }
        return dto;
    }

    private UserTypeEntity resolveUserRole(TypeUser role) {
        String roleName = role.name();
        UserTypeEntity existingRole = userTypeRepository.findByName(roleName).orElse(null);
        if (existingRole != null) {
            return existingRole;
        }
        UserTypeEntity newRole = new UserTypeEntity();
        newRole.setName(roleName);
        return userTypeRepository.save(newRole);
    }

}
