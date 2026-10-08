package com.ericnascimet.security.config;

import lombok.Builder;

@Builder
public record JwtUserData(Long userId,String email) {
}
