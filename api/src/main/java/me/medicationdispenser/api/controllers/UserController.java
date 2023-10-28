package me.medicationdispenser.api.controllers;

import java.util.List;
import java.util.Optional;
import me.medicationdispenser.api.models.User;
import me.medicationdispenser.api.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {

        return userRepository.findAll();

    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {

        return userRepository.findById(id)
                             .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                            "no user found with id " + id));

    }

    @PostMapping("/user")
    public User postUser(@RequestBody User user) {

        return userRepository.save(user);

    }

    @PutMapping("/user")
    public User putUser(@RequestBody User user) {

        if (userRepository.findById(user.getId())
                          .isPresent()) {

            userRepository.save(user);
            return user;

        } else {

            //No user found to update.
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no user found with id " + user.getId());

        }
    }

    @DeleteMapping("/user")
    public Optional<User> deleteUser(@RequestBody Long userId) {

        if (userRepository.findById(userId)
                          .isPresent()) {

            Optional<User> toRemove = userRepository.findById(userId);
            userRepository.deleteById(userId);

            return toRemove;

        } else {

            //No user found to delete.
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no user found with id " + userId);

        }
    }
}
