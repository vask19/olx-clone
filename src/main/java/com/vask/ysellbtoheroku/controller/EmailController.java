package com.vask.ysellbtoheroku.controller;

import com.vask.ysellbtoheroku.dto.UserDto;
import com.vask.ysellbtoheroku.payload.request.EmailActivationCode;
import com.vask.ysellbtoheroku.service.EmailService;
import com.vask.ysellbtoheroku.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("api/emails")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;
    private final RegistrationService registrationService;

    @GetMapping("/activation/send")
    public String  sendActivationCode(Principal principal, Model model){
        UserDto userDto = emailService.sendCodeForActivationEmailToUserEmail(principal);
        model.addAttribute("code",new EmailActivationCode());
        model.addAttribute("userDto",userDto);
        return "email/email_page";
    }
    @PostMapping("/activation/check")
    public String activationUsersEmail(@ModelAttribute("code") EmailActivationCode code, Principal principal){



        UserDto userDto = emailService.activationUsersEmail(principal,code.toInt());
        return "redirect:/logout";
    }
}
