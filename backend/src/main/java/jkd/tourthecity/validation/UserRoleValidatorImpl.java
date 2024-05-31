package jkd.tourthecity.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import jkd.tourthecity.model.ERole;
import jkd.tourthecity.model.User;
import jkd.tourthecity.service.UserService;


@Component
@RequiredArgsConstructor
public class UserRoleValidatorImpl implements UserRoleValidator{

    @Override
    public boolean isModeratorOrAdmin(User user) {
        return userHasRole(user, ERole.ROLE_MODERATOR) || userHasRole(user,ERole.ROLE_ADMIN);
    }

    @Override
    public boolean userHasRole(User user, ERole role) {
        return user.getRoles().contains(role);
    }

    @Override
    public boolean checkUserRole(ERole role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                       .anyMatch(r -> r.getAuthority().equals(role.name()));
    }

    @Override
    public boolean isModerator(User user) {
        return userHasRole(user,ERole.ROLE_MODERATOR);
    }

    @Override
    public boolean isAdmin(User user) {
        return userHasRole(user,ERole.ROLE_ADMIN);
    }
}