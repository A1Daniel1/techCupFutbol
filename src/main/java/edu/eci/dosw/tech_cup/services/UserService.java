package edu.eci.dosw.tech_cup.services;

import java.util.ArrayList;
import java.util.List;
import edu.eci.dosw.tech_cup.dto.User;
import edu.eci.dosw.tech_cup.entities.UserEntity;
import edu.eci.dosw.tech_cup.mappers.UserMapper;
import edu.eci.dosw.tech_cup.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
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
        UserEntity entity = userMapper.toEntity(user, password, academicProgram);
        UserEntity saved = userRepository.save(entity);
        return userMapper.toDto(saved);
    }

    public User updateUser(Long id, User user, String password, String academicProgram) {
        validateUserData(user, password);
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        userMapper.apply(entity, user, password, academicProgram);
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

}
