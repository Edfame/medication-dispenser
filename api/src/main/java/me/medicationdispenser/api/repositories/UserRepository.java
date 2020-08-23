package me.medicationdispenser.api.repositories;

import me.medicationdispenser.api.models.DrugUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<DrugUser, Long> {

    @Override
    Optional<DrugUser> findById(Long userId);
}
