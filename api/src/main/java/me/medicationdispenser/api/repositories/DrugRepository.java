package me.medicationdispenser.api.repositories;

import me.medicationdispenser.api.models.Drug;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugRepository extends CrudRepository<Drug, Long>{
}
