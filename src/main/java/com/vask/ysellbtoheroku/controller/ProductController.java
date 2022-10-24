package com.vask.ysellbtoheroku.controller;

import com.vask.ysellbtoheroku.dto.MessageDto;
import com.vask.ysellbtoheroku.dto.ProductDto;
import com.vask.ysellbtoheroku.dto.UserDto;
import com.vask.ysellbtoheroku.model.User;
import com.vask.ysellbtoheroku.security.CustomUserDetails;
import com.vask.ysellbtoheroku.service.ProductService;
import com.vask.ysellbtoheroku.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final UserService userService;
    @GetMapping("")
    public String allProductsPages(Model model){
        List<ProductDto> productDtoList = productService.getAll();
        model.addAttribute("productDtoList",productDtoList);

        return "product/product_page";
    }

    @GetMapping("/add")
    public String addProductPage(Model model){
        model.addAttribute("productDto",new ProductDto());
        return "product/add_product_page";
    }

    @GetMapping("/{id}")
    public String  getProduct(@PathVariable("id") int id,Model model,Principal principal){
        ProductDto productDto =  productService.getProduct(id);
        model.addAttribute("productDto",productDto);
        model.addAttribute("messageDto", new MessageDto());
        UserDto ownerDto = userService.getUserById(productDto.getUserId());
        model.addAttribute("ownerDto",ownerDto);
        return "product/product_info";
    }



    @PostMapping("")
    public String  addProduct(@RequestPart("file1") MultipartFile file1,
                              @RequestPart("file2") MultipartFile file2,
                              @RequestPart("file3") MultipartFile file3,
                                                 @ModelAttribute("productDto") ProductDto productDto
                                              ){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        productService.saveNewProduct(productDto,principal,new MultipartFile[]{file1,file2,file3});
        return "redirect:/api/products";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDto> deleteProductFromUser(@PathVariable Integer id, Principal principal){
        return new ResponseEntity<>(productService.deleteProduct(id,principal),HttpStatus.OK);

    }

}
