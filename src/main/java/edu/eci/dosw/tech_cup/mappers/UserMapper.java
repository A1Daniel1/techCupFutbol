package edu.eci.dosw.tech_cup.mappers;

import edu.eci.dosw.tech_cup.dto.User;
import edu.eci.dosw.tech_cup.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toDto(UserEntity entity) {
        User dto = new User();
        dto.setId(entity.getId().intValue());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setAge(entity.getAge());
        dto.setRole(entity.getType());
        return dto;
    }

    public UserEntity toEntity(User user, String password, String academicProgram) {
        UserEntity entity = new UserEntity();
        apply(entity, user, password, academicProgram);
        return entity;
    }

    public void apply(UserEntity entity, User user, String password, String academicProgram) {
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setAge(user.getAge());
        entity.setType(user.getRole());
        entity.setPassword(password);
        entity.setAcademicProgram(academicProgram);
    }
}
