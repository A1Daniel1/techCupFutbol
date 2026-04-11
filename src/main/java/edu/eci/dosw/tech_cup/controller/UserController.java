package edu.eci.dosw.tech_cup.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.dosw.tech_cup.dto.User;
import edu.eci.dosw.tech_cup.enums.TypeUser;
import edu.eci.dosw.tech_cup.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "Endpoints para consulta de usuarios")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    @Operation(summary = "Listar usuarios", description = "Retorna la lista de usuarios")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario por id", description = "Retorna un usuario especifico")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    @Operation(summary = "Crear usuario", description = "Registra un nuevo usuario")
    public User createUser(@RequestBody UserRequest request) {
        return userService.createUser(toDto(request), request.password(), request.academicProgram());

    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar usuario", description = "Actualiza los datos de un usuario existente")
    public User updateUser(@PathVariable Long id, @RequestBody UserRequest request) {
        return userService.updateUser(id, toDto(request), request.password(), request.academicProgram());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario por id")
    public Map<String, String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return Map.of("message", "User deleted successfully");
    }

    private User toDto(UserRequest request) {
        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setAge(request.age());
        user.setRole(request.role());
        return user;
    }

    public record UserRequest(
            String name,
            String email,
            int age,
            TypeUser role,
            String password,
            String academicProgram) {
    }

}
