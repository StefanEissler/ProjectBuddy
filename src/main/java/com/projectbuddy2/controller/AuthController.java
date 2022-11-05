package com.projectbuddy2.controller;

import com.projectbuddy2.dto.LoginDto;
import com.projectbuddy2.dto.RegisterDto;
import com.projectbuddy2.entities.User;
import com.projectbuddy2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


// Rest Controller zum Anmelden, Abmelden und neue Konten erstellen
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;


    // einen User einloggen
    @PostMapping(value = "/signin", consumes= {"application/json"})
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    //neuen User registrieren
    @PostMapping(value ="/register", consumes = {"application/json"})
    public ResponseEntity<?> registerUser(@RequestBody RegisterDto registerDto){
        if(userRepository.existsUserByName(registerDto.getName())){
            return new ResponseEntity<>("Name is already used", HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsUserByEmail(registerDto.getEmail())){
            return new ResponseEntity<>("Email is already used", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>("User is registered successfully", HttpStatus.OK);
    }

    @GetMapping(value = "/userId")
    public ResponseEntity<?> getUserId(HttpServletRequest req){
        String userName = req.getUserPrincipal().getName();
        if(!userRepository.existsUserByName(userName))
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        Optional<User> userOptional = userRepository.findByName(userName);
        Long userId = userOptional.get().getId();
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

}
