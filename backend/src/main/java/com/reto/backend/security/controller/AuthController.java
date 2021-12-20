package com.reto.backend.security.controller;

import com.reto.backend.security.dto.JwtDTO;
import com.reto.backend.security.dto.LoginAccount;
import com.reto.backend.security.dto.NewAccount;
import com.reto.backend.security.entity.Account;
import com.reto.backend.security.entity.Role;
import com.reto.backend.security.jwt.JwtProvider;
import com.reto.backend.security.service.AccountService;
import com.reto.backend.security.service.RoleService;
import com.reto.backend.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AccountService accountService;

    @Autowired
    RoleService roleService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/new")
    public ResponseEntity<?> newAccount(@Valid @RequestBody NewAccount newAccount, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(Util.formatMessage(bindingResult), HttpStatus.BAD_REQUEST);
        }
        if(accountService.existsUsername(newAccount.getUsername())){
            return new ResponseEntity("Usuario ya existe", HttpStatus.CONFLICT);
        }
        Account account = Account.builder().username(newAccount.getUsername())
                                           .password(passwordEncoder.encode(newAccount.getPassword())).build();
        Set<Role> roles = new HashSet<>();
        roles.add(this.roleService.findyByRoleName("ROLE_USER").get());
        if(newAccount.getRoles().contains("admin")){
            roles.add(this.roleService.findyByRoleName("ROLE_ADMIN").get());
        }
        account.setRoles(roles);
        this.accountService.saveAccount(account);
        return new ResponseEntity("Usuario creado",HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody LoginAccount loginAccount, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(Util.formatMessage(bindingResult), HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginAccount.getUsername(),loginAccount.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDTO jwtDTO = new JwtDTO(jwt, loginAccount.getUsername(), userDetails.getAuthorities());
        return ResponseEntity.ok(jwtDTO);
    }
}
