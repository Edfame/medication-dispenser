package me.medicationdispenser.api.controllers;

import me.medicationdispenser.api.models.Drug;
import me.medicationdispenser.api.repositories.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api")
public class DrugController {

    @Autowired
    private final DrugRepository drugRepo;

    public DrugController(DrugRepository drugRepository) {
        this.drugRepo = drugRepository;
    }

    @PostMapping("/new_take")
    public Drug newTake(@RequestBody Drug newDrugTake) {

        //TODO store new take in the database.
        newDrugTake.setDrugName(newDrugTake.getDrugName() + "_modified_by_api");

        return newDrugTake;
    }
}
