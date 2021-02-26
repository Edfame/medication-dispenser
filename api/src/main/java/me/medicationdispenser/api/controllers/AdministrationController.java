package me.medicationdispenser.api.controllers;

import me.medicationdispenser.api.models.Administration;
import me.medicationdispenser.api.models.AdministrationIdentification;
import me.medicationdispenser.api.repositories.AdministrationRepository;
import me.medicationdispenser.api.repositories.DrugRepository;
import me.medicationdispenser.api.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdministrationController {

    private final AdministrationRepository administrationRepository;
    private final DrugRepository drugRepository;
    private final UserRepository userRepository;

    public AdministrationController(AdministrationRepository administrationRepository, DrugRepository drugRepository, UserRepository userRepository) {
        this.administrationRepository = administrationRepository;
        this.drugRepository = drugRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/administrations")
    public List<Administration> getAdministrations(@RequestBody AdministrationIdentification administrationIdentification) {

        return administrationRepository.findAllByAdministrationIdentification_DrugIdAndAdministrationIdentification_UserId(administrationIdentification.getDrugId(), administrationIdentification.getUserId());
    }

    @GetMapping("/administration")
    public List<Administration> getAdministration(@RequestBody AdministrationIdentification administrationIdentification) {

        if(userRepository.findById(administrationIdentification.getUserId()).isPresent()) {

            return administrationRepository.findAllByAdministrationIdentification_UserId(administrationIdentification.getUserId());

        } else {

            //User doesn't exist.
            return null;

        }
    }

    @PostMapping("/administration")
    public Administration postAdministration(@RequestBody AdministrationIdentification administrationIdentification) {

        if(administrationRepository.findById(administrationIdentification).isEmpty()) {

            if (drugRepository.findById(administrationIdentification.getDrugId()).isPresent() &&
                userRepository.findById(administrationIdentification.getUserId()).isPresent()) {

                Administration toPost = new Administration(administrationIdentification);

                administrationRepository.save(toPost);

                return toPost;

            } else {

                //Either the user or the drug doesn't exist.
                return null;

            }

        } else {

            //The administration tuple already exists.
            return null;

        }
    }

    @PutMapping("/administration")
    public AdministrationIdentification putAdministration(@RequestBody AdministrationIdentification administrationIdentification) {

        //Does nothing so far.
        return null;
    }

    @DeleteMapping("/administration")
    public List<Administration> deleteAdministrations(@RequestBody AdministrationIdentification administrationIdentification) {

        List userAdministrations = administrationRepository.findAllByAdministrationIdentification_UserId(administrationIdentification.getUserId());

        administrationRepository.deleteAll(userAdministrations);

        return userAdministrations;
    }
}
