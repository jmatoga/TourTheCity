package jkd.tourthecity.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accomplishment")
public class Accomplishment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Integer progress;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;
}
