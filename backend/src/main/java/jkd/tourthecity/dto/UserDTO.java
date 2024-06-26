package jkd.tourthecity.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;
    private String name;
    private String surname;
    private String email;
    private int points;
    private List<String> roles;
}
