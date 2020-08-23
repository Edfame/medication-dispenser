package me.medicationdispenser.api.repositories;

import me.medicationdispenser.api.models.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Long> {

    @Override
    Optional<Drug> findById(Long aLong);

    Optional<Drug> findAllByDrugName(String drugName);

    @Override
    List<Drug> findAll();
}
