package me.medicationdispenser.api.repositories;

import java.util.List;
import java.util.Optional;
import me.medicationdispenser.api.models.Administration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrationRepository extends JpaRepository<Administration, Long> {

    Optional<List<Administration>> findAllByPrescriptionId(Long prescriptionId);

    Optional<List<Administration>> findAllByPrescriptionUserId(Long usedId);

    Optional<List<Administration>> findAdministrationsByPrescriptionId(Long prescriptionId);

    Optional<List<Administration>> findAllById(Long id);


}
