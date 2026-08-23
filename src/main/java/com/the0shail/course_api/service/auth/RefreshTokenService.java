package com.the0shail.course_api.service.auth;

import com.the0shail.course_api.entity.RefreshToken;
import com.the0shail.course_api.entity.User;
import com.the0shail.course_api.repository.RefreshTokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HexFormat;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.refresh-ttl-days}")
    private long refreshTtlDays;

    @Transactional
    public String issue(User user) {
        String rawToken = UUID.randomUUID().toString();

        RefreshToken entity = new RefreshToken();
        entity.setTokenHash(sha256Hex(rawToken));
        entity.setUser(user);
        entity.setExpiresAt(Instant.now().plus(refreshTtlDays, ChronoUnit.DAYS));

        refreshTokenRepository.save(entity);
        return rawToken;
    }

    @Transactional
    public User validateAndRotate(String rawToken) {
        RefreshToken stored = refreshTokenRepository.findByTokenHash(sha256Hex(rawToken))
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


    @SneakyThrows
    private static String sha256Hex(String raw) {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] b = md.digest(raw.getBytes(StandardCharsets.UTF_8));
        return HexFormat.of().formatHex(b);
    }
}
