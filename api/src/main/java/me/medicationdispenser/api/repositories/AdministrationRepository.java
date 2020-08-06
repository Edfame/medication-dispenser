package me.medicationdispenser.api.repositories;

import me.medicationdispenser.api.models.Administration;
import me.medicationdispenser.api.models.AdministrationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministrationRepository extends JpaRepository<Administration, AdministrationId> {

    @Override
    List<Administration> findAllById(AdministrationId administrationIds);
}
