package com.vask.ysellbtoheroku.util;
import com.vask.ysellbtoheroku.payload.request.SignupRequest;
import com.vask.ysellbtoheroku.service.CustomUserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class UserValidator implements Validator {


    private final CustomUserDetailsServiceImpl userDetailsService;

    @Override
    public boolean supports(Class<?> clazz) {
        return SignupRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {


        //TODO : create equals service
        SignupRequest user = (SignupRequest) target;
        try {
            userDetailsService.loadUserByUsername(user.getUsername());

        }catch (UsernameNotFoundException ignored){
            return;
        }

        errors.rejectValue("username","","User with such username already exists");





    }
}
