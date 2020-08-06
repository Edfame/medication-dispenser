package me.medicationdispenser.api.controllers;

import me.medicationdispenser.api.models.Drug;
import me.medicationdispenser.api.repositories.DrugRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DrugController {

    private final DrugRepository drugRepo;

    public DrugController(DrugRepository drugRepository) {
        this.drugRepo = drugRepository;
    }

    @GetMapping("/get_drug")
    public Drug getDrug() {
        return new Drug("newDrug");
    }

    @PostMapping("/new_take")
    public Drug newTake(@RequestBody Drug newDrugTake) {

        //TODO store new take in the database.
        newDrugTake.setDrugName(newDrugTake.getDrugName() + "_modified_by_api");

        return newDrugTake;
    }
}
