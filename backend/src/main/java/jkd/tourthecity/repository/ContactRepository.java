package jkd.tourthecity.repository;

import jkd.tourthecity.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, String> {
}
