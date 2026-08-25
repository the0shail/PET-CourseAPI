package com.the0shail.course_api.provider;

import com.the0shail.course_api.entity.User;
import com.the0shail.course_api.exception.TypeException;
import com.the0shail.course_api.exception.exception.UnauthorizedException;
import com.the0shail.course_api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CurrentUserProvider
{
    private final UserRepository userRepository;

    public User require() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken) {
            throw new UnauthorizedException("Требуется аутентификация", TypeException.UNAUTHORIZED);
        }
        return userRepository.findUserByEmail(auth.getName())
                .orElseThrow(() -> new UnauthorizedException("Пользователь не найден", TypeException.UNAUTHORIZED));
    }
}
