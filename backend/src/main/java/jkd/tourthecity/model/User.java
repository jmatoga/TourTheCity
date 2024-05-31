package jkd.tourthecity.model;

import jakarta.validation.constraints.Email;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String surname;

    @Email(message = "{errors.invalid_email}")
    private String email;

    private String password;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime created;

    @ElementCollection(targetClass=ERole.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="user_role")
    @Column(name="role")
    private Set<ERole> roles = new HashSet<>();

    private Integer points;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public RefreshToken refreshToken;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
