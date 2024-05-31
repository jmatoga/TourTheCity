package jkd.tourthecity.validation;

import jkd.tourthecity.model.User;
import jkd.tourthecity.model.ERole;

public interface UserRoleValidator {

    boolean isModeratorOrAdmin(User user);

    boolean userHasRole(User user, ERole role);

    boolean checkUserRole(ERole role);

    boolean isModerator(User currentAdmin);

    boolean isAdmin(User user);
}
