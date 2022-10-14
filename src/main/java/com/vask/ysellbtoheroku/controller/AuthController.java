package com.vask.ysellbtoheroku.controller;

import com.vask.ysellbtoheroku.payload.request.SignupRequest;
import com.vask.ysellbtoheroku.service.RegistrationService;
import com.vask.ysellbtoheroku.util.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/auth/")
public class AuthController {
    private final RegistrationService registrationService;

    private final UserValidator userValidator;


    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("signupRequest") SignupRequest signupRequest){
        return "auth/registration";

    }



    @PostMapping("/registration")
    public String registration(@ModelAttribute("signupRequest") @Valid SignupRequest signupRequest,
                               BindingResult bindingResult
                               ){
        userValidator.validate(signupRequest,bindingResult);

        if (bindingResult.hasErrors()){
            return "auth/registration";
        }
        registrationService.register(signupRequest);
        return "redirect:/login";

    }
}
