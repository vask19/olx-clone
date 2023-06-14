package com.vask.ysellbtoheroku.controller;

import com.vask.ysellbtoheroku.dto.ProductDto;
import com.vask.ysellbtoheroku.dto.UserDto;
import com.vask.ysellbtoheroku.model.enums.Role;
import com.vask.ysellbtoheroku.service.ProductService;
import com.vask.ysellbtoheroku.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api")
public class MainController {
    private final ProductService productService;
    private final UserService userService;



    @GetMapping("")
    public String homePage(Model model){
        List<ProductDto> productDtoList = productService.getAll();
        model.addAttribute("productDtoList",productDtoList);
        return "home";
    }

    @GetMapping("/settings")
    public String getSettingsPage(Model model){
        model.addAttribute("userDto", new UserDto());

        return "settings/settings";
    }





}

