package me.medicationdispenser.api.repositories;

import java.util.List;
import java.util.Optional;
import me.medicationdispenser.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    Optional<User> findById(Long userId);

    List<User> findAll();

}
