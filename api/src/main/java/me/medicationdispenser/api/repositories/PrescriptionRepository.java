package me.medicationdispenser.api.repositories;

import me.medicationdispenser.api.models.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    Optional<Long> findByUserIdAndMedicineId(Long userId, Long medicineId);

    Optional<List<Prescription>> findAllByUserId(Long userId);

    Optional<List<Prescription>> findAllByMedicineId(Long medicineId);

}
