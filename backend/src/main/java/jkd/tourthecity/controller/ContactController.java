package jkd.tourthecity.controller;


import jkd.tourthecity.model.Contact;
import jkd.tourthecity.repository.ContactRepository;
import jkd.tourthecity.security.payload.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Log4j2
public class ContactController {
    private final ContactRepository contactRepository;
    @PostMapping("/contact")
    public ResponseEntity<MessageResponse> saveContact(@RequestBody Contact contact) {
        contactRepository.save(contact);
        return ResponseEntity.ok(new MessageResponse("Contact saved successfully!"));
    }

    @GetMapping("/contact")
    public ResponseEntity<List<Contact>> getAllContacts() {
        return ResponseEntity.ok(contactRepository.findAll());
    }
}
