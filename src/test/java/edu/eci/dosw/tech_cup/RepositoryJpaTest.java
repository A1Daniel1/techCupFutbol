package edu.eci.dosw.tech_cup;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import edu.eci.dosw.tech_cup.dto.User;
import edu.eci.dosw.tech_cup.entities.RoleEntity;
import edu.eci.dosw.tech_cup.entities.UserEntity;
import edu.eci.dosw.tech_cup.enums.TypeUser;
import edu.eci.dosw.tech_cup.mappers.UserMapper;
import edu.eci.dosw.tech_cup.repositories.RoleRepository;
import edu.eci.dosw.tech_cup.repositories.UserRepository;
import edu.eci.dosw.tech_cup.services.UserService;

@ExtendWith(MockitoExtension.class)
class RepositoryJpaTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldGetUsersFromRepository() {
        UserEntity entity = new UserEntity();
        entity.setId(1L);
        User dto = buildUserDto("Ana", "ana@eci.edu.co");

        when(userRepository.findAll()).thenReturn(List.of(entity));
        when(userMapper.toDto(entity)).thenReturn(dto);

        List<User> users = userService.getUsers();

        assertEquals(1, users.size());
        assertEquals("Ana", users.get(0).getName());
    }

    @Test
    void shouldGetUserById() {
        UserEntity entity = new UserEntity();
        entity.setId(10L);
        User dto = buildUserDto("Carlos", "carlos@eci.edu.co");

        when(userRepository.findById(10L)).thenReturn(Optional.of(entity));
        when(userMapper.toDto(entity)).thenReturn(dto);

        User user = userService.getUserById(10L);

        assertEquals("Carlos", user.getName());
    }

    @Test
    void shouldCreateUser() {
        User input = buildUserDto("Laura", "laura@eci.edu.co");
        UserEntity entity = new UserEntity();
        UserEntity saved = new UserEntity();
        RoleEntity role = new RoleEntity();
        role.setName("GEST");
        saved.setId(3L);
        User savedDto = buildUserDto("Laura", "laura@eci.edu.co");
        savedDto.setId(3);

        when(userMapper.toEntity(input, "secret", "Sistemas")).thenReturn(entity);
        when(roleRepository.findByName("GEST")).thenReturn(Optional.of(role));
        when(userRepository.save(entity)).thenReturn(saved);
        when(userMapper.toDto(saved)).thenReturn(savedDto);

        User created = userService.createUser(input, "secret", "Sistemas");

        assertEquals(3, created.getId());
    }

    @Test
    void shouldUpdateUser() {
        User input = buildUserDto("Luis", "luis@eci.edu.co");
        UserEntity existing = new UserEntity();
        RoleEntity role = new RoleEntity();
        role.setName("GEST");
        existing.setId(7L);
        existing.setEmail("luis@eci.edu.co");

        when(userRepository.findById(7L)).thenReturn(Optional.of(existing));
        when(roleRepository.findByName("GEST")).thenReturn(Optional.of(role));
        when(userRepository.save(existing)).thenReturn(existing);
        when(userMapper.toDto(existing)).thenReturn(input);

        User updated = userService.updateUser(7L, input, "secret", "Industrial");

        verify(userMapper).apply(existing, input, "secret", "Industrial");
        assertEquals("Luis", updated.getName());
    }

    @Test
    void shouldDeleteExistingUser() {
        when(userRepository.existsById(4L)).thenReturn(true);

        userService.deleteUser(4L);

        verify(userRepository).deleteById(4L);
    }

    @Test
    void shouldFailWhenDeletingMissingUser() {
        when(userRepository.existsById(99L)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> userService.deleteUser(99L));
    }

    @Test
    void shouldFailWhenGetUserByIdDoesNotExist() {
        when(userRepository.findById(404L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> userService.getUserById(404L));
    }

    @Test
    void shouldFailWhenUpdateUserDoesNotExist() {
        User input = buildUserDto("NoExiste", "no@eci.edu.co");
        when(userRepository.findById(404L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> userService.updateUser(404L, input, "secret", "Sistemas"));
    }

    @Test
    void shouldFailWhenCreateUserWithoutName() {
        User input = buildUserDto("", "ana@eci.edu.co");

        assertThrows(IllegalArgumentException.class, () -> userService.createUser(input, "secret", "Sistemas"));
    }

    @Test
    void shouldFailWhenCreateUserWithoutEmail() {
        User input = buildUserDto("Ana", "");

        assertThrows(IllegalArgumentException.class, () -> userService.createUser(input, "secret", "Sistemas"));
    }

    @Test
    void shouldFailWhenCreateUserWithoutRole() {
        User input = buildUserDto("Ana", "ana@eci.edu.co");
        input.setRole(null);

        assertThrows(IllegalArgumentException.class, () -> userService.createUser(input, "secret", "Sistemas"));
    }

    @Test
    void shouldFailWhenCreateUserWithoutPassword() {
        User input = buildUserDto("Ana", "ana@eci.edu.co");

        assertThrows(IllegalArgumentException.class, () -> userService.createUser(input, "", "Sistemas"));
    }

    @Test
    void shouldFailWhenCreateUserWithInvalidAge() {
        User input = buildUserDto("Ana", "ana@eci.edu.co");
        input.setAge(0);

        assertThrows(IllegalArgumentException.class, () -> userService.createUser(input, "secret", "Sistemas"));
    }

    @Test
    void shouldFailWhenCreateUserPayloadIsNull() {
        assertThrows(IllegalArgumentException.class, () -> userService.createUser(null, "secret", "Sistemas"));
    }

    private User buildUserDto(String name, String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setAge(21);
        user.setRole(TypeUser.GEST);
        return user;
    }
}
