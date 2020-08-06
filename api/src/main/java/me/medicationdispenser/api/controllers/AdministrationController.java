package me.medicationdispenser.api.controllers;

import me.medicationdispenser.api.models.Administration;
import me.medicationdispenser.api.models.AdministrationId;
import me.medicationdispenser.api.repositories.AdministrationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

@RestController
@RequestMapping("/api")
public class AdministrationController {

    private final AdministrationRepository administrationRepository;

    public AdministrationController(AdministrationRepository administrationRepository) {
        this.administrationRepository = administrationRepository;
    }

    @GetMapping("/get_administration")
    public Administration getAdministration(@RequestBody AdministrationId administrationId) {
        return administrationRepository.findAllById(administrationId);
    }

    @PostMapping("/new_administration")
    public Administration registerAdministration(@RequestBody Administration administration) {

        administrationRepository.save(administration);

        return administration;
    }

    //TODO Not working properly.
    @PutMapping("/update_administration")
    public Administration updateAdministration(@RequestBody Administration administration) {

        administrationRepository.save(administration);

        return administration;
    }
}
