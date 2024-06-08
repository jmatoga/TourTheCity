package jkd.tourthecity.repository;

import jkd.tourthecity.model.Accomplishment;
import jkd.tourthecity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccomplishmentRepository extends JpaRepository<Accomplishment, String> {

    List<Accomplishment> findAllByUserId(User userId);
}
