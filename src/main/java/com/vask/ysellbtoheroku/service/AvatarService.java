package com.vask.ysellbtoheroku.service;

import com.vask.ysellbtoheroku.model.Avatar;
import com.vask.ysellbtoheroku.model.Image;
import com.vask.ysellbtoheroku.repository.AvatarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AvatarService {
    private final AvatarRepository avatarRepository;

    public Avatar findById(Long id) {
        return avatarRepository.findById(id).orElse(new Avatar());
    }



    public Image toImageEntity(MultipartFile file){
        try {
            return Image.builder()
                    .name(file.getName())
                    .originalFileName(file.getOriginalFilename())
                    .contentType(file.getContentType())
                    .size(file.getSize())
                    .bytes(file.getBytes())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return new Image();
        }
    }

}
