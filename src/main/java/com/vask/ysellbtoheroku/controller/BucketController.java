package com.vask.ysellbtoheroku.controller;


import com.vask.ysellbtoheroku.dto.BucketDto;
import com.vask.ysellbtoheroku.dto.ProductDto;
import com.vask.ysellbtoheroku.service.BucketService;
import com.vask.ysellbtoheroku.service.ProductService;
import com.vask.ysellbtoheroku.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/buckets")
public class BucketController {
    private final UserService userService;
    private final ProductService productService;
    private final BucketService bucketService;



    @GetMapping("/add/{id}")
    public String  addProductToBucket(@PathVariable("id") int id, Principal principal){
        bucketService.addProduct(id,principal);

        return "redirect:/api/products/" + id;


    }

    @GetMapping()
    public String  getBucket(Principal principal,Model model){
        BucketDto bucketDto = bucketService.getBucketByUser(principal);
        List<ProductDto> productDtoList =  (bucketDto.getProducts() == null
                ? Collections.emptyList()
                : bucketDto.getProducts());
        model.addAttribute("productDtoList",productDtoList);
        return "bucket/bucket_page";




    }

    @DeleteMapping("/{id}")
    public String  deleteProduct(@PathVariable Integer id, Principal principal){
        bucketService.deleteProduct(id,principal);
        return "redirect:/api/buckets/";
    }

}
