package me.medicationdispenser.api.controllers;

import me.medicationdispenser.api.models.DrugUser;
import me.medicationdispenser.api.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DrugUserController {

    private final UserRepository userRepository;

    public DrugUserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<DrugUser> getAllUsers() {

        return userRepository.findAll();

    }

    @GetMapping("/user")
    public Optional<DrugUser> getUser(@RequestBody Long drugUserId) {

        return userRepository.findById(drugUserId);

    }

    @PostMapping("/user")
    public DrugUser postUser(@RequestBody String drugUserName) {

        DrugUser toAdd = new DrugUser(drugUserName);

        userRepository.save(toAdd);

        return toAdd;

    }

    @PutMapping("/user")
    public DrugUser postUser(@RequestBody DrugUser drugUser) {

        if (userRepository.findById(drugUser.getDrugUserId()).isPresent()) {


            userRepository.save(drugUser);
            return drugUser;

        } else {

            //Don't add a new user with PUT method.
            return null;

        }
    }

    @DeleteMapping("/user")
    public Optional<DrugUser> deleteUser(@RequestBody Long drugUserId) {

        if (userRepository.findById(drugUserId).isPresent()) {

            Optional<DrugUser> toRemove = userRepository.findById(drugUserId);
            userRepository.deleteById(drugUserId);

            return toRemove;

        } else {

            //No user found to delete.
            return  null;

        }
    }
}
