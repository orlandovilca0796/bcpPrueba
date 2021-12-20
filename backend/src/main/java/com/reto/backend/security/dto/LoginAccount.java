package com.reto.backend.security.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class LoginAccount {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
