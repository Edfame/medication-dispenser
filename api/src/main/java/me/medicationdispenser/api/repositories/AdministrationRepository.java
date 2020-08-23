package me.medicationdispenser.api.repositories;

import me.medicationdispenser.api.models.Administration;
import me.medicationdispenser.api.models.AdministrationIdentification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministrationRepository extends JpaRepository<Administration, AdministrationIdentification> {

    //Get all Administrations for the pair (drugId, userId).
    List<Administration> findAllByAdministrationIdentification_DrugIdAndAdministrationIdentification_UserId(long administrationIdentification_drugId, long administrationIdentification_userId);

    //Get all Administrations for the userId.
    List<Administration> findAllByAdministrationIdentification_UserId(long administrationIdentification_userId);

}
