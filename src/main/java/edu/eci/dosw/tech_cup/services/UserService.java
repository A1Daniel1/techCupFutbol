package edu.eci.dosw.tech_cup.services;

import java.util.ArrayList;
import java.util.List;
import edu.eci.dosw.tech_cup.dto.User;
import edu.eci.dosw.tech_cup.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return new ArrayList<>();
    }

}
