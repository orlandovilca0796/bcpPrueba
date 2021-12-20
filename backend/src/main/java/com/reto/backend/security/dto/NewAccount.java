package com.reto.backend.security.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class NewAccount {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private Set<String> roles = new HashSet<>();
}
