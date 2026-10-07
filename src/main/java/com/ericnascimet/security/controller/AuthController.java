package com.ericnascimet.security.controller;

import com.ericnascimet.security.config.TokenConfig;
import com.ericnascimet.security.repositories.UserRepository;
import com.ericnascimet.security.dto.request.LoginRequest;
import com.ericnascimet.security.dto.request.RegisterUserRequest;
import com.ericnascimet.security.dto.response.LoginResponse;
import com.ericnascimet.security.dto.response.RegisterResponse;
import com.ericnascimet.security.entities.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager,
                          TokenConfig tokenConfig)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(),request.password());
        Authentication authetication = authenticationManager.authenticate(userAndPass);

        User user = (User) authetication.getPrincipal();
        String token = tokenConfig.generateToken(user);
        return ResponseEntity.ok( new LoginResponse(token)) ;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterUserRequest request){
        User user = new User();
         user.setName(request.name());
         user.setEmail(request.email());
         user.setPassword(passwordEncoder.encode(request.password()));

         userRepository.save(user);
         return  ResponseEntity.status(HttpStatus.CREATED).body(new RegisterResponse(user.getName(),user.getEmail()));
    }


}
