package com.ericnascimet.security.dto.request;

import com.ericnascimet.security.entities.Role;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record RegisterUserRequest(
        @NotEmpty(message = "nome é obrigatório") String name,
        @NotEmpty(message = "email obrigatório") String email,
        @NotEmpty(message = "senha é obrigatória") String password,
        List<Role> role) {
}
