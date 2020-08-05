package me.medicationdispenser.api.repositories;

import me.medicationdispenser.api.models.DrugUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<DrugUser, Long> {
}
