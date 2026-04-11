package edu.eci.dosw.tech_cup.mappers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import edu.eci.dosw.tech_cup.dto.User;
import edu.eci.dosw.tech_cup.entities.RoleEntity;
import edu.eci.dosw.tech_cup.entities.UserEntity;
import edu.eci.dosw.tech_cup.enums.TypeUser;

@Component
public class UserMapper {

    public User toDto(UserEntity entity) {
        User dto = new User();
        dto.setId(entity.getId().intValue());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setAge(entity.getAge());
        if (entity.getRoles() != null && !entity.getRoles().isEmpty()) {
            String firstRoleName = entity.getRoles().iterator().next().getName();
            dto.setRole(TypeUser.valueOf(firstRoleName));
        } else if (entity.getType() != null) {
            dto.setRole(entity.getType());
        }
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
        Set<RoleEntity> roles = new HashSet<>();
        if (user.getRole() != null) {
            RoleEntity role = new RoleEntity();
            role.setName(user.getRole().name());
            roles.add(role);
        }
        entity.setRoles(roles);
        entity.setType(user.getRole());
        entity.setPassword(password);
        entity.setAcademicProgram(academicProgram);
    }
}
