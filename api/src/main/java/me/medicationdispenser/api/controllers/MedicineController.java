package me.medicationdispenser.api.controllers;

import java.util.List;
import java.util.Optional;
import me.medicationdispenser.api.models.Medicine;
import me.medicationdispenser.api.repositories.MedicineRepository;
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
public class MedicineController {

    private final MedicineRepository medicineRepository;

    public MedicineController(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @GetMapping("/medicines")
    public List<Medicine> getMedicines() {

        return medicineRepository.findAll();
    }

    @GetMapping("/medicine/{id}")
    public Medicine getMedicine(@PathVariable Long id) {

        return medicineRepository.findById(id)
                                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                                "no medicine found with id " + id));
    }

    @PostMapping("/medicine")
    public Medicine postMedicine(@RequestBody Medicine medicine) {

        return medicineRepository.save(medicine);

    }

    @PutMapping("/medicine")
    public Medicine putMedicine(@RequestBody Medicine medicine) {

        if (medicineRepository.findById(medicine.getId())
                              .isPresent()) {

            medicineRepository.save(medicine);
            return medicine;

        } else {

            //No medicine found to update.
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no medicine found with id " + medicine.getId());

        }

    }

    @DeleteMapping("/medicine")
    public Optional<Medicine> deleteMedicine(@RequestBody Long medicineId) {

        if (medicineRepository.findById(medicineId)
                              .isPresent()) {

            Optional<Medicine> toRemove = medicineRepository.findById(medicineId);
            medicineRepository.deleteById(medicineId);

            return toRemove;

        } else {

            //No medicine found to delete.
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No medicine found with id " + medicineId);

        }
    }
}
