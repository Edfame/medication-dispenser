package me.medicationdispenser.api.controllers;

import me.medicationdispenser.api.models.Drug;
import me.medicationdispenser.api.repositories.DrugRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DrugController {

    private final DrugRepository drugRepo;

    public DrugController(DrugRepository drugRepository) {
        this.drugRepo = drugRepository;
    }

    @GetMapping("/get_drug")
    public Optional<Drug> getDrug(@RequestBody Drug drug) {

        return drugRepo.findById(drug.getDrugId());
    }

    @PostMapping("/new_take")
    public Drug newTake(@RequestBody Drug newDrugTake) {

        drugRepo.save(newDrugTake);

        return newDrugTake;
    }
}
