package com.ericnascimet.security.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record RegisterUserRequest(
        @NotEmpty(message = "nome é obrigatório") String name,
        @NotEmpty(message = "email obrigatório") String email,
        @NotEmpty(message = "senha é obrigatória") String password) {
}
