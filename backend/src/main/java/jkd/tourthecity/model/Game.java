package jkd.tourthecity.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer gameStatus;
    private Integer points;

    @OneToOne
    @JoinColumn(name = "map_id")
    private Map mapId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User userId;
}
