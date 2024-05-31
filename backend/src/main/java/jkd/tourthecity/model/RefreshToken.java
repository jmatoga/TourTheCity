package jkd.tourthecity.model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity(name = "refreshtoken")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String token;

    private Instant expiryDate;

}
