package com.the0shail.course_api.service;

import com.the0shail.course_api.dto.request.user.SignUpRequest;
import com.the0shail.course_api.dto.request.user.UpdateProfileRequest;
import com.the0shail.course_api.dto.response.user.UserDetailsDto;
import com.the0shail.course_api.dto.response.user.UserPublicDto;
import com.the0shail.course_api.entity.User;
import com.the0shail.course_api.entity.UserProfile;
import com.the0shail.course_api.entity.enumerate.PublicationStatus;
import com.the0shail.course_api.entity.enumerate.Role;
import com.the0shail.course_api.exception.TypeException;
import com.the0shail.course_api.exception.exception.BadRequestException;
import com.the0shail.course_api.exception.exception.NotFoundException;
import com.the0shail.course_api.mapper.UserMapper;
import com.the0shail.course_api.mapper.UserProfileMapper;
import com.the0shail.course_api.repository.CourseRepository;
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
    private final CourseRepository courseRepository;
    private final UserMapper userMapper;
    private final UserProfileMapper userProfileMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDetailsDto register(SignUpRequest request) {
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
    public UserPublicDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Пользователь не найден", TypeException.NOT_FOUND));
        return userMapper.toPublicDto(user, courseRepository.countByAuthorIdAndStatus(user.getId(), PublicationStatus.PUBLISHED));
    }

    @Transactional(readOnly = true)
    public UserDetailsDto findByEmail(String email){
        return userMapper.toDto(userRepository.findUserByEmail(email).orElseThrow(() -> new NotFoundException("Пользователь не найден", TypeException.NOT_FOUND)));
    }

    @Transactional
    public UserDetailsDto updateProfile(UpdateProfileRequest request, String email) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден", TypeException.NOT_FOUND));

        UserProfile profile = user.getProfile();

        if (profile == null){
            profile = new UserProfile();
            user.setProfile(profile);
        }

        userProfileMapper.updateProfile(request, profile);

        return userMapper.toDto(user);
    }

    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден", TypeException.NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public User getByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден", TypeException.NOT_FOUND));
    }
}
