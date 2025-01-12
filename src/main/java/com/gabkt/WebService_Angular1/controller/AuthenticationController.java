package com.gabkt.WebService_Angular1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabkt.WebService_Angular1.model.User;
import com.gabkt.WebService_Angular1.repository.UserDao;
import com.gabkt.WebService_Angular1.security.TokenService;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDao userDao;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = "";
        if (auth.isAuthenticated()) {
            token = tokenService.generateToken((User) auth.getPrincipal());
        }
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User data) {
        if (this.userDao.buscarPorEmail(data.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email ja cadastrado.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        User newUser = new User(data.getName(), data.getEmail(), encryptedPassword, data.getRole());

        User user = userDao.inserirUser(newUser);
        System.out.println("Usuario cadastrado: " + user);

        if (user != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao registrar usuário.");
        }
    }
}
