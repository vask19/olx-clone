package com.vask.ysellbtoheroku.service;

import com.vask.ysellbtoheroku.dto.BucketDto;
import com.vask.ysellbtoheroku.dto.ProductDto;
import com.vask.ysellbtoheroku.exception.ProductNotFoundException;
import com.vask.ysellbtoheroku.mapper.ProductMapper;
import com.vask.ysellbtoheroku.model.Bucket;
import com.vask.ysellbtoheroku.model.Product;
import com.vask.ysellbtoheroku.model.User;
import com.vask.ysellbtoheroku.repository.BucketRepository;
import com.vask.ysellbtoheroku.repository.ProductRepository;
import com.vask.ysellbtoheroku.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BucketService {
    private final BucketRepository bucketRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper = ProductMapper.MAPPER;

    @Transactional
    public Bucket createBucket(User user) {
        Bucket bucket = new Bucket();
        bucket.setUser(user);
        bucket.setProducts(new ArrayList<>());
        log.info( "a bucket for user was created");
        return bucketRepository.save(bucket);

    }
    public User getUserByPrincipal(Principal principal) {
        return userRepository.findFirstByUsername(principal.getName()).orElseThrow(()
                -> new UsernameNotFoundException("User not exists with username: " + principal.getName()));
    }

    private List<Product> getCollectRefBooksByIds(List<Integer> bookIds) {
        return bookIds.stream()
                .map(productRepository::getOne)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductDto addProduct(Integer bookId, Principal principal) {
        User user =  getUserByPrincipal(principal);
        Bucket bucket = bucketRepository.findByUser(user).orElse(null);
        if (bucket == null){
            bucket = createBucket(user);}
        List<Product> products = bucket.getProducts();
        List<Product> newProductList = products == null ? new ArrayList<>() : new ArrayList<>(products);
        newProductList.addAll(getCollectRefBooksByIds(Collections.singletonList(bookId)));
        bucket.setProducts(newProductList);
        log.info( "a product was added to bucket");
        bucketRepository.save(bucket);
        return productMapper.fromProduct(productRepository.getReferenceById(bookId));


    }

    @Transactional
    public BucketDto getBucketByUser(Principal principal){
        User user =  getUserByPrincipal(principal);
        Bucket bucket = bucketRepository.findByUser(user).orElse(null);
        if (bucket == null) {
            bucket = createBucket(user);
        }
        List<Product> products = bucket.getProducts();
        List<Integer> productIds = products.stream().map(Product::getId).collect(Collectors.toList());
        BucketDto bucketDto = new BucketDto();
        bucketDto.setProducts(productMapper.fromProductList(getCollectRefBooksByIds(productIds)));
        return bucketDto;

    }
    @Transactional
    public ProductDto deleteProduct(Integer id, Principal principal) {
        User user =  getUserByPrincipal(principal);
        Bucket bucket = bucketRepository.findByUser(user).orElse(null);
        Product product = productRepository.findById(id).orElseThrow(()
            -> new ProductNotFoundException(id));
        log.info( "a product from bucket was deleted ");
        bucket.getProducts().remove(product);
        bucketRepository.save(bucket);
        return productMapper.fromProduct(product);

    }
}

