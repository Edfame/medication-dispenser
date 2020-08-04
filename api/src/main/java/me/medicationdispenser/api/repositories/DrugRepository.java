package me.medicationdispenser.api.repositories;

import me.medicationdispenser.api.models.Drug;
import org.springframework.data.repository.CrudRepository;

public interface DrugRepository extends CrudRepository<Drug, Long>{
}
