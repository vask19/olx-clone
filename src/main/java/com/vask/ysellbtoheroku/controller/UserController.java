package com.vask.ysellbtoheroku.controller;

import com.vask.ysellbtoheroku.dto.ProductDto;
import com.vask.ysellbtoheroku.dto.UserDto;
import com.vask.ysellbtoheroku.mapper.ProductMapper;
import com.vask.ysellbtoheroku.service.ProductService;
import com.vask.ysellbtoheroku.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ProductService productService;
    private ProductMapper mapper = ProductMapper.MAPPER;
    @PutMapping("/update")
    private ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.updateUser(userDto),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public String  getUsersPage(@PathVariable("id") Integer id, Model model ){
        UserDto userDto = userService.getUserById(id);
        List<ProductDto> productDtoList = productService.getAllByUserId(id);
        userDto.setProductDtoList(productDtoList);
        model.addAttribute("userDto",userDto);
        return "user/user_page";

    }
}
