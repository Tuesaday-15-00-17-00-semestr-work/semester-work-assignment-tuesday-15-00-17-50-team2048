package jspeetr.semPraca.library.Controllers;

import jakarta.validation.Valid;
import jspeetr.semPraca.library.Repositories.UserRepository;
import jspeetr.semPraca.library.Records.Users;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UserRepository userRepository;

    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    //--------------------GET METHODS--------------------------------------------------------------------//
    @GetMapping("/fetch")
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{user_id}")
    Optional<Users> findById(@PathVariable int user_id){
        return userRepository.findById(user_id);
    }

    //--------------------CREATE METHODS--------------------------------------------------------------------//
    @PostMapping("/create")
    void create(@Valid @RequestBody Users user) {
        userRepository.create(user);
    }

    //--------------------DELETE METHODS--------------------------------------------------------------------//
    @DeleteMapping("/delete/{user_id}")
    public void deleteUser(@PathVariable int user_id) {
        userRepository.delete(user_id);
    }
}