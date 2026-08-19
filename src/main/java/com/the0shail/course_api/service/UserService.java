package com.the0shail.course_api.service;

import com.the0shail.course_api.dto.request.user.SignUpRequest;
import com.the0shail.course_api.dto.request.user.UpdateProfileRequest;
import com.the0shail.course_api.dto.response.user.UserDto;
import com.the0shail.course_api.entity.Course;
import com.the0shail.course_api.entity.User;
import com.the0shail.course_api.entity.UserProfile;
import com.the0shail.course_api.entity.enumerate.Role;
import com.the0shail.course_api.exception.exception.BadRequestException;
import com.the0shail.course_api.exception.TypeException;
import com.the0shail.course_api.exception.exception.NotFoundException;
import com.the0shail.course_api.mapper.UserMapper;
import com.the0shail.course_api.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDto register(SignUpRequest request) {
        if (userRepository.existsUserByEmail(request.email()))
            throw new BadRequestException("Email уже занят", TypeException.EMAIL_ALREADY_TAKEN);

        User user = userMapper.toEntity(request);

        UserProfile userProfile = new UserProfile();

        if (request.firstName() != null) userProfile.setFirstName(request.firstName());
        if (request.lastName() != null) userProfile.setLastName(request.lastName());

        user.setRole(Role.STUDENT);
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setProfile(userProfile);

        User saved = userRepository.save(user);

        log.info("зарегистрирован пользователь id={}, email={}, password={}", saved.getId(), saved.getEmail(), saved.getPassword());

        return userMapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public UserDto findById(Long id) {
        return userMapper.toDto(userRepository.findById(id).orElseThrow(() -> new NotFoundException("Пользователь не найден", TypeException.NOT_FOUND)));
    }

    @Transactional(readOnly = true)
    public UserDto findByEmail(String email){
        return userMapper.toDto(userRepository.findUserByEmail(email).orElseThrow(() -> new NotFoundException("Пользователь не найден", TypeException.NOT_FOUND)));
    }

    @Transactional
    public UserDto updateProfile(UpdateProfileRequest request, String email) {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new NotFoundException("Пользователь не найден", TypeException.NOT_FOUND));

        UserProfile userProfile = user.getProfile();

        log.info("Был пользователь first_name={}, last_name={}, bio={}", userProfile.getFirstName(), userProfile.getLastName(), userProfile.getBio());

        if (request.firstName() != null) userProfile.setFirstName(request.firstName());
        if (request.lastName() != null) userProfile.setLastName(request.lastName());
        if (request.bio() != null) userProfile.setBio(request.bio());
        if (request.avatarUrl() != null) userProfile.setAvatarUrl(request.avatarUrl());

        log.info("Стал пользователь first_name={}, last_name={}, bio={}", userProfile.getFirstName(), userProfile.getLastName(), userProfile.getBio());

        return null;
    }

    @Transactional(readOnly = true)
    public User getById(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден", TypeException.NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public User getByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден", TypeException.NOT_FOUND));
    }
}
