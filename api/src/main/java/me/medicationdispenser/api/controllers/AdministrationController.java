package me.medicationdispenser.api.controllers;

import java.time.LocalDateTime;
import java.util.List;
import me.medicationdispenser.api.models.Administration;
import me.medicationdispenser.api.models.Prescription;
import me.medicationdispenser.api.repositories.AdministrationRepository;
import me.medicationdispenser.api.repositories.MedicineRepository;
import me.medicationdispenser.api.repositories.PrescriptionRepository;
import me.medicationdispenser.api.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class AdministrationController {

    private final AdministrationRepository administrationRepository;
    private final MedicineRepository medicineRepository;
    private final UserRepository userRepository;
    private final PrescriptionRepository prescriptionRepository;

    public AdministrationController(AdministrationRepository administrationRepository,
                                    MedicineRepository medicineRepository, UserRepository userRepository,
                                    PrescriptionRepository prescriptionRepository) {
        this.administrationRepository = administrationRepository;
        this.medicineRepository = medicineRepository;
        this.userRepository = userRepository;
        this.prescriptionRepository = prescriptionRepository;
    }

    @GetMapping("/administrations/ByPrescriptionId/{id}")
    public List<Administration> getAdministrationsByPrescription(@PathVariable Long id) {

        prescriptionRepository.findById(id)
                              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                             "no prescription found with id " +
                                                                                 id));

        return administrationRepository.findAllByPrescriptionId(id)
                                       .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                                      "no administrations found for prescriptionId " +
                                                                                          id));

    }

    @GetMapping("/administrations/ByUserId/{id}")
    public List<Administration> getAdministrationsByUserId(@PathVariable Long id) {

        userRepository.findById(id)
                      .orElseThrow(
                          () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "no user found with id " + id));

        return administrationRepository.findAllByPrescriptionId(id)
                                       .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                                      "no administrations found for id " +
                                                                                          id));

    }

    @GetMapping("/administration/{id}")
    public List<Administration> getAdministration(@PathVariable Long id) {

        return administrationRepository.findAllById(id)
                                       .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                                      "no administrations found for id " +
                                                                                          id));

    }

    @PostMapping("/administration")
    public Administration postAdministration(@RequestBody Administration administration) {

        Long prescriptionId = administration.getPrescription()
                                            .getId();
        Prescription administrationPrescription = prescriptionRepository.findById(prescriptionId)
                                                                        .orElseThrow(() -> new ResponseStatusException(
                                                                            HttpStatus.NOT_FOUND,
                                                                            "no prescription found with id " +
                                                                                prescriptionId));
        administration.setPrescription(administrationPrescription);

        if (administration.getAdministerDate() == null) {
            administration.setAdministerDate(LocalDateTime.now());
        }

        administrationRepository.save(administration);

        return administration;

    }

    @PutMapping("/administration")
    public Administration putAdministration(@RequestBody Administration administration) {

        administrationRepository.findById(administration.getId())
                                .ifPresent(x -> administrationRepository.save(administration));

        return administration;

    }

    @DeleteMapping("/administration/{id}")
    public Administration deleteAdministration(@PathVariable Long id) {

        Administration administration = administrationRepository.findById(id)
                                                                .orElseThrow(() -> new ResponseStatusException(
                                                                    HttpStatus.NOT_FOUND,
                                                                    "no administration found with id " + id));

        administrationRepository.delete(administration);

        return administration;
    }

    @DeleteMapping("/administration/ByUserId/{id}")
    public List<Administration> deleteAdministrations(@PathVariable Long id) {

        List<Administration> administrations = administrationRepository.findAllByPrescriptionUserId(id)
                                                                       .orElseThrow(() -> new ResponseStatusException(
                                                                           HttpStatus.NOT_FOUND,
                                                                           "no administrations found for id " +
                                                                               id));

        administrationRepository.deleteAll(administrations);

        return administrations;
    }
}
