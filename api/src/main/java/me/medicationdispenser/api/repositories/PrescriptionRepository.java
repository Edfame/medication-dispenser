package me.medicationdispenser.api.repositories;

import me.medicationdispenser.api.models.Prescription;
import org.springframework.data.repository.CrudRepository;

public interface PrescriptionRepository extends CrudRepository<Prescription, Long> {
}
