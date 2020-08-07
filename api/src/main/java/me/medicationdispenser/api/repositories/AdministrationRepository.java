package me.medicationdispenser.api.repositories;

import me.medicationdispenser.api.models.Administration;
import me.medicationdispenser.api.models.AdministrationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministrationRepository extends JpaRepository<Administration, AdministrationId> {

    //Get all Administrations for the pair (drugId, userId).
    List<Administration> findAllByAdministrationId_DrugIdAndAdministrationId_UserId(long administrationId_drugId, long administrationId_userId);

    //Get all Administrations for the userId.
    List<Administration> findAllByAdministrationId_UserId(long administrationId_userId);

}
