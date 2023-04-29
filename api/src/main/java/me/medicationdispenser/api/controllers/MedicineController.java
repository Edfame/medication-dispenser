package me.medicationdispenser.api.controllers;

import me.medicationdispenser.api.models.Medicine;
import me.medicationdispenser.api.repositories.MedicineRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/medicine")
    public Medicine getMedicine(@RequestBody Long id) {

        return medicineRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medicine not found"));
    }

    @PostMapping("/medicine")
    public Medicine postMedicine(@RequestBody Medicine medicine) {

        return medicineRepository.save(medicine);

    }

    @PutMapping("/medicine")
    public Medicine putMedicine(@RequestBody Medicine medicine) {

        if (medicineRepository.findById(medicine.getId()).isPresent()) {

            medicineRepository.save(medicine);
            return medicine;

        } else {

            //No medicine found to update.
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Medicine not found");

        }

    }

    @DeleteMapping("/medicine")
    public Optional<Medicine> deleteMedicine(@RequestBody Long medicineId) {

        if (medicineRepository.findById(medicineId).isPresent()) {

            Optional<Medicine> toRemove = medicineRepository.findById(medicineId);
            medicineRepository.deleteById(medicineId);

            return toRemove;

        } else {

            //No medicine found to delete.
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Medicine not found");

        }
    }
}
