package com.vask.ysellbtoheroku.service;
import com.vask.ysellbtoheroku.mapper.SignupMapper;
import com.vask.ysellbtoheroku.model.Avatar;
import com.vask.ysellbtoheroku.model.User;
import com.vask.ysellbtoheroku.model.enums.Role;
import com.vask.ysellbtoheroku.payload.request.SignupRequest;
import com.vask.ysellbtoheroku.repository.AvatarRepository;
import com.vask.ysellbtoheroku.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SignupMapper signupMapper = SignupMapper.MAPPER;
    private final AvatarRepository avatarRepository;

    @Transactional
    public SignupRequest register(SignupRequest signupRequest) {
        User user = signupMapper.toUser(signupRequest);
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_NOT_CONFIRMED_USER);
        userRepository.save(user);
        log.info("user has been saved");
        log.info( "new user has been registered");
        return signupMapper.fromUser(user);
    }



}

