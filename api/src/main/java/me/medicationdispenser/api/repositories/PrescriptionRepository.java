package me.medicationdispenser.api.repositories;

import me.medicationdispenser.api.models.Prescription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends CrudRepository<Prescription, Long> {
}
