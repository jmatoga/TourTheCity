package jkd.tourthecity.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "maps")
public class Map {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String location;
    private String name;
    private Integer difficulty;
}
