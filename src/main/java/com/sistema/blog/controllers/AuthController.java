package com.sistema.blog.controllers;

import com.sistema.blog.dto.LoginDTO;
import com.sistema.blog.dto.PublicationDTO;
import com.sistema.blog.dto.UserDTO;
import com.sistema.blog.entities.Publication;
import com.sistema.blog.entities.Role;
import com.sistema.blog.entities.User;
import com.sistema.blog.exceptions.BadRequestException;
import com.sistema.blog.repositories.RoleRepository;
import com.sistema.blog.repositories.UserRepository;
import com.sistema.blog.security.JwtAuthResponseDTO;
import com.sistema.blog.security.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponseDTO> authenticateUser(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token= jwtTokenProvider.generateToken(authentication);
        return  ResponseEntity.ok(new JwtAuthResponseDTO(token, "Bearer"));
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO){
        if(this.userRepository.existsByEmail(userDTO.getEmail())) throw new BadRequestException("Ya existe un usuario con ese correo");
        if (this.userRepository.existsByUsername(userDTO.getUsername()))throw  new BadRequestException("Ya existe un usuario con ese username");
        User user=new User();
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(this.bCryptPasswordEncoder.encode(userDTO.getPassword()));

        Role roles= this.roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));

        this.userRepository.save(user);
        return  new ResponseEntity<>("Usuario Registrado exitosamente", HttpStatus.CREATED);

    }

}
