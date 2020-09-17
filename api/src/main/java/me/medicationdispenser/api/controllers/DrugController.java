package me.medicationdispenser.api.controllers;

import me.medicationdispenser.api.models.Drug;
import me.medicationdispenser.api.repositories.DrugRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DrugController {

    private final DrugRepository drugRepo;

    public DrugController(DrugRepository drugRepository) {
        this.drugRepo = drugRepository;
    }

    @GetMapping("/drugs")
    public List<Drug> getAllDrugs() {

        return drugRepo.findAll();
    }

    @GetMapping("/drug")
    public Optional<Drug> getDrug(@RequestBody Long drugId) {

        return drugRepo.findById(drugId);
    }

    @PostMapping("/drug")
    public Drug postDrug(@RequestBody Drug drug) {

        if (drugRepo.findAllByDrugName(drug.getDrugName()).isPresent()) {

            return null;

        } else {

            return drugRepo.save(drug);
        }
    }

    @PutMapping("/drug")
    public Drug putDrug(@RequestBody Drug drug) {

        drugRepo.findById(drug.getDrugId()).ifPresent(
                toEdit -> {
                    drugRepo.save(drug);
                }
        );

        return drug;
    }

    @DeleteMapping("/drug")
    public Drug deleteDrug(@RequestBody Drug drug) {

        drugRepo.delete(drug);

        return drug;
    }
}
