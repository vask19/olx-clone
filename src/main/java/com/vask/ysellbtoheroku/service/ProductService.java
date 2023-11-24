package com.vask.ysellbtoheroku.service;

import com.vask.ysellbtoheroku.dto.ProductDto;
import com.vask.ysellbtoheroku.exception.ProductNotFoundException;
import com.vask.ysellbtoheroku.mapper.ProductMapper;
import com.vask.ysellbtoheroku.model.Image;
import com.vask.ysellbtoheroku.model.Product;
import com.vask.ysellbtoheroku.model.User;
import com.vask.ysellbtoheroku.repository.ProductRepository;
import com.vask.ysellbtoheroku.repository.UserRepository;
import com.vask.ysellbtoheroku.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ProductMapper mapper = ProductMapper.MAPPER;

    @Transactional
    public List<ProductDto> getAll() {

        return mapper.fromProductList(productRepository.findAll());
    }

    @Transactional
    public List<ProductDto> getAllByUserId(Integer id) {
        User user = userRepository.findFirstById(id).orElseThrow();
        return mapper.fromProductList(productRepository.findAllByUser(user));
    }

    @Transactional
    public ProductDto saveNewProduct(ProductDto productDto, CustomUserDetails principal, MultipartFile[] multipartFiles) {
        Product product = mapper.toProduct(productDto);
        List<Image> images = Arrays.stream(multipartFiles)
                .filter(el -> el.getSize() != 0)
                .map(this::toImageEntity)
                .collect(Collectors.toList());
        images.forEach(image -> {
            image.setProduct(product);
            product.getImages().add(image);
        });
        images.get(0).setPreviewImage(true);
        product.setUser(getUserByPrincipal(principal));
        log.info("a new product with photos was saved");
        Product bookFromDb = productRepository.save(product);
        bookFromDb.setPreviewImageId(bookFromDb.getImages().get(0).getId());
        productRepository.save(product);
        log.info("a new product with preview photo was saved");
        return mapper.fromProduct(productRepository.save(product));
    }

    public Image toImageEntity(MultipartFile file) {
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


    public User getUserByPrincipal(CustomUserDetails principal) {
        return userRepository.findFirstByUsername(principal.getUser().getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + principal.getUsername()));
    }

    @Transactional
    public ProductDto getProduct(int id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ProductNotFoundException(id));
        return mapper.fromProduct(product);
    }

    @Transactional
    public ProductDto deleteProduct(Integer id, Principal principal) {
        Product product = productRepository.findById(id).orElseThrow(()
                -> new ProductNotFoundException(id));
        if (product.getUser().getUsername().equals(principal.getName())) {
            product.getBuckets()
                    .forEach(bucket -> bucket.getProducts().remove(product));
            ProductDto productDto = mapper.fromProduct(product);
            productRepository.delete(product);
            log.info("a product was deleted");
            return productDto;
        } else {
            return new ProductDto();
        }
    }
}
