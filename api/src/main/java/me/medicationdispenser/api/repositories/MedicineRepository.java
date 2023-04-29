package me.medicationdispenser.api.repositories;

import me.medicationdispenser.api.models.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    @Override
    Optional<Medicine> findById(Long aLong);

    Optional<Medicine> findAllByName(String name);

    @Override
    List<Medicine> findAll();

}
