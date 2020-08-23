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

    @GetMapping("/get_administrations")
    public List<Administration> getAdministrationByDrugAndUser(@RequestBody AdministrationIdentification administrationIdentification) {

        return administrationRepository.findAllByAdministrationIdentification_DrugIdAndAdministrationIdentification_UserId(administrationIdentification.getDrugId(), administrationIdentification.getUserId());
    }

    @GetMapping("/get_user_administrations")
    public List<Administration> getAdministrationByUser(@RequestBody AdministrationIdentification administrationIdentification) {

        return administrationRepository.findAllByAdministrationIdentification_UserId(administrationIdentification.getUserId());
    }

    @PostMapping("/new_administration")
    public Administration registerAdministration(@RequestBody AdministrationIdentification administrationIdentification) {

        if(administrationRepository.findById(administrationIdentification).isEmpty()) {

            if (drugRepository.findById(administrationIdentification.getDrugId()).isPresent() &&
                userRepository.findById(administrationIdentification.getUserId()).isPresent()) {

                Administration toAdd = new Administration(administrationIdentification);

                administrationRepository.save(toAdd);

                return toAdd;

            } else {

                //Either the user or the drug doesn't exist.
                return null;

            }

        } else {

            //The administration tuple already exists.
            return null;

        }
    }

    @PutMapping("/update_administration")
    public Administration updateAdministration(@RequestBody Administration administration) {

        administrationRepository.save(administration);

        return administration;
    }

    @DeleteMapping("/delete_user_administrations")
    public List<Administration> deleteUserAdministrations(@RequestBody AdministrationIdentification administrationIdentification) {

        List userAdministrations = administrationRepository.findAllByAdministrationIdentification_UserId(administrationIdentification.getUserId());

        administrationRepository.deleteAll(userAdministrations);

        return userAdministrations;
    }
}
