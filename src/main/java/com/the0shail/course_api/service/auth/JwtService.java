package com.the0shail.course_api.service.auth;

import com.the0shail.course_api.dto.request.auth.RefreshRequest;
import com.the0shail.course_api.dto.request.user.SignInRequest;
import com.the0shail.course_api.dto.response.auth.AuthTokens;
import com.the0shail.course_api.entity.User;
import com.the0shail.course_api.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtEncoder jwtEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;

    @Value("${jwt.ttl-minutes}")
    private long ttlMinutes;

    @Transactional
    public AuthTokens login(SignInRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password()));

        User user = userService.getByEmail(auth.getName());

        return new AuthTokens(
                generateAccessToken(user.getEmail(), auth.getAuthorities()),
                refreshTokenService.issue(user),
                "Bearer",
                ttlMinutes * 60
        );
    }

    @Transactional
    public AuthTokens refresh(RefreshRequest request) {
        User user = refreshTokenService.validateAndRotate(request.refreshToken());

        List<GrantedAuthority> authorities =
                List.of(new SimpleGrantedAuthority(user.getRole().name()));

        return new AuthTokens(
                generateAccessToken(user.getEmail(), authorities),
                refreshTokenService.issue(user),
                "Bearer",
                ttlMinutes * 60
        );
    }

    public String generateAccessToken(String email, Collection<? extends GrantedAuthority> authorities) {
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("course-api")
                .issuedAt(now)
                .expiresAt(now.plus(ttlMinutes, ChronoUnit.MINUTES))
                .subject(email)
                .claim("roles", authorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .filter(a -> !a.startsWith("FACTOR_"))   // помните лишние FACTOR_*
                        .toList())
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS256).build(), claims)).getTokenValue();
    }
}
