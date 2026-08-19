package com.the0shail.course_api.service.auth;

import com.the0shail.course_api.entity.RefreshToken;
import com.the0shail.course_api.entity.User;
import com.the0shail.course_api.repository.RefreshTokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.refresh-ttl-days}")
    private long refreshTtlDays;

    @Transactional
    public String issue(User user) {
        RefreshToken entity = new RefreshToken();
        entity.setToken(UUID.randomUUID().toString());
        entity.setUser(user);
        entity.setExpiresAt(Instant.now().plus(refreshTtlDays, ChronoUnit.DAYS));

        return refreshTokenRepository.save(entity).getToken();
    }

    @Transactional
    public User validateAndRotate(String rawToken) {
        RefreshToken stored = refreshTokenRepository.findByToken(rawToken)
                .orElseThrow(() -> new BadCredentialsException("Недействительный refresh-токен"));

        if (stored.isRevoked() || stored.getExpiresAt().isBefore(Instant.now()))
            throw new BadCredentialsException("Недействительный refresh-токен");

        stored.setRevoked(true);   // ротация: одноразовое использование
        return stored.getUser();
    }

    @Transactional
    public void revokeAll(Long userId) {
        refreshTokenRepository.revokeAllByUserId(userId);
    }
}
