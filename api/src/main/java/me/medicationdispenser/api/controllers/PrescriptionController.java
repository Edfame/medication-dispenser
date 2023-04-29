package me.medicationdispenser.api.controllers;

import me.medicationdispenser.api.models.Administration;
import me.medicationdispenser.api.models.Prescription;
import me.medicationdispenser.api.repositories.MedicineRepository;
import me.medicationdispenser.api.repositories.PrescriptionRepository;
import me.medicationdispenser.api.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PrescriptionController {

    private final PrescriptionRepository prescriptionRepository;
    private final UserRepository userRepository;
    private final MedicineRepository medicineRepository;

    public PrescriptionController(PrescriptionRepository prescriptionRepository, UserRepository userRepository, MedicineRepository medicineRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.userRepository = userRepository;
        this.medicineRepository = medicineRepository;
    }

    @GetMapping("/prescriptionsByUserId")
    public List<Prescription> getPrescriptionsByUserId(@RequestBody Long userId) {

        return prescriptionRepository.findAllByUserId(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "prescriptions not found"));

    }

    @GetMapping("/prescriptionsByMedicineId")
    public List<Prescription> getPrescriptionsByMedicineId(@RequestBody Long medicineId) {

        return prescriptionRepository.findAllByMedicineId(medicineId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "prescriptions not found"));

    }

    @GetMapping("/prescriptionByUserIdAndMedicineId")
    public Long getPrescription(@RequestBody Long userId, Long medicineId) {

        return prescriptionRepository.findByUserIdAndMedicineId(userId, medicineId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "prescriptions not found"));

    }

    @GetMapping("/prescription")
    public Prescription getPrescription(@RequestBody Long id) {

        return prescriptionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "prescription not found"));

    }

    @PostMapping("/prescription")
    public Prescription postPrescription(@RequestBody Prescription prescription) {

        Long userId = prescription.getUser().getId();
        userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));

        Long medicineId = prescription.getMedicine().getId();
        medicineRepository.findById(medicineId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "medicine not found"));

        prescriptionRepository.save(prescription);

        return prescription;

    }

    @PutMapping("/prescription")
    public Prescription putAdministration(@RequestBody Prescription prescription) {

        prescriptionRepository.findById(prescription.getId()).ifPresent(x -> prescriptionRepository.save(prescription));

        return prescription;

    }

    @DeleteMapping("/prescription")
    public Prescription deleteAdministration(@RequestBody Long id) {

        Prescription prescription = prescriptionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "prescription not found"));

        prescriptionRepository.delete(prescription);

        return prescription;
    }

    @DeleteMapping("/prescriptionByUserId")
    public List<Prescription> deleteAdministrations(@RequestBody Long userId) {

        List<Prescription> prescriptions = prescriptionRepository.findAllByUserId(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "prescriptions not found"));

        prescriptionRepository.deleteAll(prescriptions);

        return prescriptions;
    }
}
