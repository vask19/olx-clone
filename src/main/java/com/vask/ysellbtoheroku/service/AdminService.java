package com.vask.ysellbtoheroku.service;

import com.vask.ysellbtoheroku.dto.UserDto;
import com.vask.ysellbtoheroku.mapper.UserMapper;
import com.vask.ysellbtoheroku.model.User;
import com.vask.ysellbtoheroku.repository.ProductRepository;
import com.vask.ysellbtoheroku.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {
    private final UserRepository userRepository;
    private final ProductRepository bookRepository;
    private final UserMapper userMapper = UserMapper.MAPPER;

    @Transactional
    public UserDto putUsersStatus(Integer id, boolean isActive) {
        User user = userRepository.findFirstById(id).orElseThrow(()
                -> new UsernameNotFoundException(""));
        user.setActive(isActive);
        userRepository.save(user);
        log.info("a user was saved");
        return userMapper.fromUser(user);
    }
}
