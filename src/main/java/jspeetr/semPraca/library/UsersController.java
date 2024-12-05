package jspeetr.semPraca.library;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UserRepository userRepository;

    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("")
    void create(@Valid @RequestBody Users user) {
        userRepository.create(user);
    }

    @GetMapping("")
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }
}