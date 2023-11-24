package com.vask.ysellbtoheroku.service;

import com.vask.ysellbtoheroku.dto.UserDto;
import com.vask.ysellbtoheroku.mapper.UserMapper;
import com.vask.ysellbtoheroku.model.User;
import com.vask.ysellbtoheroku.model.enums.Role;
import com.vask.ysellbtoheroku.payload.request.EmailReceiver;
import com.vask.ysellbtoheroku.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Collections;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final UserMapper userMapper = UserMapper.MAPPER;
    private final UserRepository userRepository;
    @Value(value = "${spring.mail.sender.email}")
    private String senderEmail;

    public void sendEmailWithText(EmailReceiver emailReceiver) {
        var message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setSubject(emailReceiver.getSubject());
        message.setText(emailReceiver.getText());
        emailReceiver.getEmails().forEach(email -> {
            message.setTo(email);
            javaMailSender.send(message);
        });
        log.info("messages for emails were sent");
    }

    @Transactional
    public UserDto sendCodeForActivationEmailToUserEmail(Principal principal) {
        User user = getUserByPrincipal(principal);
        int code = (new Random().nextInt(100000, 900000));
        user.setEmailActivationCode(code);
        String text = "Your registration code: " + code;
        EmailReceiver emailReceiver = EmailReceiver.builder()
                .emails(Collections.singletonList(user.getEmail()))
                .subject("Registration on ySellb")
                .text(text)
                .build();
        sendEmailWithText(emailReceiver);
        userRepository.save(user);
        log.info("Email with activation code was sent to user");
        return userMapper.fromUser(user);
    }

    @Transactional
    public UserDto activationUsersEmail(Principal principal, Integer usersAuthCode) {
        User user = getUserByPrincipal(principal);
        if (user.getEmailActivationCode() == usersAuthCode) {
            user.setActiveEmail(true);
            user.setRole(Role.ROLE_USER);
            user = userRepository.save(user);
            log.info("User sent his activation code to the application ");
            log.info("user was activated");
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            authentication.setAuthenticated(false);

        } else {
            log.info("User sent a false activation code");
        }
        return userMapper.fromUser(user);
    }

    public User getUserByPrincipal(Principal principal) {
        return userRepository.findFirstByUsername(principal.getName()).orElseThrow(()
                -> new UsernameNotFoundException("User not found with username: " + principal.getName()));
    }

}
