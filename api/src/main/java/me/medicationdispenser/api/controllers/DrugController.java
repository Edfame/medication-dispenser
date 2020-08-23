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

    @GetMapping("/get_all_drugs")
    public List<Drug> getAllDrugs() {

        return drugRepo.findAll();
    }

    @GetMapping("/get_drug")
    public Optional<Drug> getDrug(@RequestBody Long drugId) {

        return drugRepo.findById(drugId);
    }

    @PostMapping("/new_drug")
    public Drug newDrug(@RequestBody Drug drug) {

        if (drugRepo.findAllByDrugName(drug.getDrugName()).isPresent()) {

            return null;

        } else {

            return drugRepo.save(drug);
        }
    }

    @PutMapping("/edit_drug")
    public Drug editDrug(@RequestBody Drug drug) {

        drugRepo.findById(drug.getDrugId()).ifPresent(
                toEdit -> {
                    drugRepo.save(drug);
                }
        );

        return drug;
    }

    @DeleteMapping("/delete_drug")
    public Drug deleteDrug(@RequestBody Drug drug) {

        drugRepo.delete(drug);

        return drug;
    }
}
