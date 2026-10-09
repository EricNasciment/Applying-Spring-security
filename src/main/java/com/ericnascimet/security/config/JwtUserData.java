package com.ericnascimet.security.config;

import lombok.Builder;

import java.util.List;

@Builder
public record JwtUserData(Long userId, String email, List<String> role) {
}
