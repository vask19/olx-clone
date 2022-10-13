package com.vask.ysellbtoheroku.service;

import com.vask.ysellbtoheroku.model.Avatar;
import com.vask.ysellbtoheroku.repository.AvatarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AvatarService {
    private final AvatarRepository avatarRepository;

    public Avatar findById(Long id) {
        return avatarRepository.findById(id).orElse(new Avatar());
    }

}
