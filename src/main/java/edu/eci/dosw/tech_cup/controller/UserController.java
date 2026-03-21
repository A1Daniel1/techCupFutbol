package edu.eci.dosw.tech_cup.controller;

import edu.eci.dosw.tech_cup.services.UserService;
import edu.eci.dosw.tech_cup.dto.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "Endpoints para consulta de usuarios")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    @Operation(summary = "Listar usuarios", description = "Retorna la lista de usuarios")
    public List<User> getUsers() {
        return userService.getUsers();
    }

}
