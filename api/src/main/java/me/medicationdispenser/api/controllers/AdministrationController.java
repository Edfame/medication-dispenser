package me.medicationdispenser.api.controllers;

import me.medicationdispenser.api.models.Administration;
import me.medicationdispenser.api.models.Prescription;
import me.medicationdispenser.api.repositories.AdministrationRepository;
import me.medicationdispenser.api.repositories.MedicineRepository;
import me.medicationdispenser.api.repositories.PrescriptionRepository;
import me.medicationdispenser.api.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdministrationController {

    private final AdministrationRepository administrationRepository;
    private final MedicineRepository medicineRepository;
    private final UserRepository userRepository;
    private final PrescriptionRepository prescriptionRepository;

    public AdministrationController(AdministrationRepository administrationRepository, MedicineRepository medicineRepository, UserRepository userRepository, PrescriptionRepository prescriptionRepository) {
        this.administrationRepository = administrationRepository;
        this.medicineRepository = medicineRepository;
        this.userRepository = userRepository;
        this.prescriptionRepository = prescriptionRepository;
    }

    @GetMapping("/administrationsByPrescription")
    public List<Administration> getAdministrationsByPrescription(@RequestBody Prescription prescription) {

        Long prescriptionId = prescription.getId();
        prescriptionRepository.findById(prescriptionId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "prescription not found"));

        return administrationRepository.findAllByPrescriptionId(prescriptionId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "administrations not found"));

    }

    @GetMapping("/administrationsByUserId")
    public List<Administration> getAdministrationsByUserId(@RequestBody Long userId) {

        List<Prescription> prescriptions = prescriptionRepository.findAllByUserId(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        return administrationRepository.findAllByPrescriptionId(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "administrations not found"));

    }

    @GetMapping("/administration")
    public List<Administration> getAdministration(@RequestBody Long id) {

        return administrationRepository.findAllById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "administrations not found"));

    }

    @PostMapping("/administration")
    public Administration postAdministration(@RequestBody Administration administration) {

        Long prescriptionId = administration.getPrescription().getId();
        prescriptionRepository.findById(prescriptionId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "prescription not found"));

        Long userId = administration.getPrescription().getUser().getId();
        userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));

        Long medicineId = administration.getPrescription().getMedicine().getId();
        medicineRepository.findById(medicineId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "medicine not found"));

        administrationRepository.save(administration);

        return administration;

    }

    @PutMapping("/administration")
    public Administration putAdministration(@RequestBody Administration administration) {

        administrationRepository.findById(administration.getId()).ifPresent(x -> administrationRepository.save(administration));

        return administration;

    }

    @DeleteMapping("/administration")
    public Administration deleteAdministration(@RequestBody Long id) {

        Administration administration = administrationRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "administration not found"));

        administrationRepository.delete(administration);

        return administration;
    }

    @DeleteMapping("/administrationByUserId")
    public List<Administration> deleteAdministrations(@RequestBody Long userId) {

        List<Administration> administrations = administrationRepository.findAllByPrescriptionUserId(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "administrations not found"));

        administrationRepository.deleteAll(administrations);

        return administrations;
    }
}
