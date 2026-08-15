package com.the0shail.course_api.service;

import com.the0shail.course_api.dto.request.user.CreateUserRequest;
import com.the0shail.course_api.dto.response.user.UserDto;
import com.the0shail.course_api.entity.User;
import com.the0shail.course_api.entity.enumerate.Role;
import com.the0shail.course_api.exception.BadRequestException;
import com.the0shail.course_api.exception.TypeException;
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
    public UserDto register(CreateUserRequest request){
        if (userRepository.existsUserByEmail(request.email()))
            throw new BadRequestException("Email уже занят", TypeException.EMAIL_ALREADY_TAKEN);

        User user = userMapper.toEntity(request);

        user.setRole(Role.STUDENT);
        user.setPassword(passwordEncoder.encode(request.password()));

        User saved = userRepository.save(user);

        log.info("зарегистрирован пользователь id={}, email={}, password={}",
                saved.getId(), saved.getEmail(), saved.getPassword());

        return userMapper.toDto(saved);
    }
}
