package me.medicationdispenser.api.controllers;

import java.util.List;
import me.medicationdispenser.api.models.Medicine;
import me.medicationdispenser.api.models.Prescription;
import me.medicationdispenser.api.models.User;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class PrescriptionController {

    private final PrescriptionRepository prescriptionRepository;
    private final UserRepository userRepository;
    private final MedicineRepository medicineRepository;

    public PrescriptionController(PrescriptionRepository prescriptionRepository, UserRepository userRepository,
                                  MedicineRepository medicineRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.userRepository = userRepository;
        this.medicineRepository = medicineRepository;
    }

    @GetMapping("/prescriptions/ByUserId/{id}")
    public List<Prescription> getPrescriptionsByUserId(@PathVariable Long id) {

        return prescriptionRepository.findAllByUserId(id)
                                     .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                                    "no prescriptions found for id " +
                                                                                        id));

    }

    @GetMapping("/prescriptions/ByMedicineId/{id}")
    public List<Prescription> getPrescriptionsByMedicineId(@PathVariable Long id) {

        return prescriptionRepository.findAllByMedicineId(id)
                                     .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                                    "no prescriptions found for userId " +
                                                                                        id));

    }

    @GetMapping("/prescription/ByUserId/{userId}/ByMedicineId/{medicineId}")
    public Long getPrescription(@RequestParam Long userId, @RequestParam Long medicineId) {

        return prescriptionRepository.findByUserIdAndMedicineId(userId, medicineId)
                                     .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                                    "no prescriptions found for userId " +
                                                                                        userId + " and medicineId " +
                                                                                        medicineId));

    }

    @GetMapping("/prescription/{id}")
    public Prescription getPrescription(@PathVariable Long id) {

        return prescriptionRepository.findById(id)
                                     .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                                    "no prescription found with id " +
                                                                                        id));

    }

    @PostMapping("/prescription")
    public Prescription postPrescription(@RequestBody Prescription prescription) {

        Long userId = prescription.getUser()
                                  .getId();
        User prescriptionUser = userRepository.findById(userId)
                                              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                                             "no user found with id " +
                                                                                                 userId));
        prescription.setUser(prescriptionUser);

        Long medicineId = prescription.getMedicine()
                                      .getId();
        Medicine prescriptionMedicine = medicineRepository.findById(medicineId)
                                                          .orElseThrow(
                                                              () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                                                "no medicine found with id " +
                                                                                                    medicineId));
        prescription.setMedicine(prescriptionMedicine);

        prescriptionRepository.save(prescription);

        return prescription;

    }

    @PutMapping("/prescription")
    public Prescription putAdministration(@RequestBody Prescription prescription) {

        prescriptionRepository.findById(prescription.getId())
                              .ifPresent(x -> prescriptionRepository.save(prescription));

        return prescription;

    }

    @DeleteMapping("/prescription")
    public Prescription deleteAdministration(@RequestBody Long id) {

        Prescription prescription = prescriptionRepository.findById(id)
                                                          .orElseThrow(
                                                              () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                                                "no prescription found with id " +
                                                                                                    id));

        prescriptionRepository.delete(prescription);

        return prescription;
    }

    @DeleteMapping("/prescription/ByUserId/{id}")
    public List<Prescription> deleteAdministrations(@RequestBody Long userId) {

        List<Prescription> prescriptions = prescriptionRepository.findAllByUserId(userId)
                                                                 .orElseThrow(() -> new ResponseStatusException(
                                                                     HttpStatus.NOT_FOUND,
                                                                     "no prescriptions found with userId " + userId));

        prescriptionRepository.deleteAll(prescriptions);

        return prescriptions;
    }
}
