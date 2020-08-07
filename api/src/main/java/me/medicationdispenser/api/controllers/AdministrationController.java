package me.medicationdispenser.api.controllers;

import me.medicationdispenser.api.models.Administration;
import me.medicationdispenser.api.models.AdministrationId;
import me.medicationdispenser.api.repositories.AdministrationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdministrationController {

    private final AdministrationRepository administrationRepository;

    public AdministrationController(AdministrationRepository administrationRepository) {
        this.administrationRepository = administrationRepository;
    }

    @GetMapping("/get_administrations")
    public List<Administration> getAdministrationByDrugAndUser(@RequestBody AdministrationId administrationId) {

        return administrationRepository.findAllByAdministrationId_DrugIdAndAdministrationId_UserId(administrationId.getDrugId(), administrationId.getUserId());
    }

    @GetMapping("/get_user_administrations")
    public List<Administration> getAdministrationByUser(@RequestBody AdministrationId administrationId) {

        return administrationRepository.findAllByAdministrationId_UserId(administrationId.getUserId());
    }

    @PostMapping("/new_administration")
    public Administration registerAdministration(@RequestBody Administration administration) {

        administrationRepository.save(administration);

        return administration;
    }

    @PutMapping("/update_administration")
    public Administration updateAdministration(@RequestBody Administration administration) {

        administrationRepository.save(administration);

        return administration;
    }

    @DeleteMapping("/delete_user_administrations")
    public List<Administration> deleteUserAdministrations(@RequestBody AdministrationId administrationId) {

        List userAdministrations = administrationRepository.findAllByAdministrationId_UserId(administrationId.getUserId());

        administrationRepository.deleteAll(userAdministrations);

        return userAdministrations;
    }
}
