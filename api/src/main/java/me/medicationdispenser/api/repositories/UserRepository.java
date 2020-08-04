package me.medicationdispenser.api.repositories;

import me.medicationdispenser.api.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
