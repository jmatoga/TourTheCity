package jkd.tourthecity.repository;

import jkd.tourthecity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    @Query(value = "SELECT name from users where id = ?1", nativeQuery = true)
    List<String> findAllByUserId(String userId);

    List<User> findAll();

    boolean existsByEmail(String email);
}
