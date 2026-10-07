package com.ericnascimet.security.controller;

import com.ericnascimet.security.repositories.UserRepository;
import com.ericnascimet.security.dto.request.LoginRequest;
import com.ericnascimet.security.dto.request.RegisterUserRequest;
import com.ericnascimet.security.dto.response.LoginResponse;
import com.ericnascimet.security.dto.response.RegisterResponse;
import com.ericnascimet.security.entities.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private UserRepository userRepository;

    public AuthController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        return null;
    }

    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterUserRequest request){
        User user = new User();
         user.setName(request.name());
         user.setEmail(request.email());
         user.setPassword(request.password());

         userRepository.save(user);
         return  ResponseEntity.status(HttpStatus.CREATED).body(new RegisterResponse(user.getName(),user.getEmail()));
    }


}
