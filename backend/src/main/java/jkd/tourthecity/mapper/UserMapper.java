package jkd.tourthecity.mapper;

import jkd.tourthecity.dto.UserDTO;
import jkd.tourthecity.model.User;
import jkd.tourthecity.security.payload.request.SignUpRequest;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import jkd.tourthecity.service.UserService;

@Mapper(imports = String.class, componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    protected UserService userService;

    @Mapping(target="email", source = "email")
    @Mapping(target="password", source = "password")
    public abstract User mapToUser(SignUpRequest signupRequest);

    @InheritInverseConfiguration(name = "mapToUser")
    @Named("mapToUserDTO")
    public abstract UserDTO mapToUserDTO(User user);


}