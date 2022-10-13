package com.vask.ysellbtoheroku.controller;

import com.vask.ysellbtoheroku.model.Avatar;
import com.vask.ysellbtoheroku.model.User;
import com.vask.ysellbtoheroku.repository.UserRepository;
import com.vask.ysellbtoheroku.service.AvatarService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayInputStream;

@Controller
@RequestMapping("/api/avatars/")
@RequiredArgsConstructor
public class AvatarController {
    private final UserRepository userRepository;

    private final AvatarService avatarService;
    @GetMapping("/{username}")
    public ResponseEntity<?> getAvatarById(@PathVariable("username") String  username) {
        User user = userRepository.findFirstByUsername(username).get();
        Avatar avatar = avatarService.findById(user.getAvatar().getId());
        return ResponseEntity.ok()
                .header("fileName",avatar.getOriginalFileName())
                .contentType(MediaType.valueOf(avatar.getContentType()))
                .contentLength(avatar.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(avatar.getBytes())));


    }
}
