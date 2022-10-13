package com.vask.ysellbtoheroku.service;
import com.vask.ysellbtoheroku.model.Image;
import com.vask.ysellbtoheroku.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageService {
    private final ImageRepository imageRepository;

    public Image findById(Long id){
        return imageRepository.findById(id).orElse(new Image());
    }
}
