package org.hae.sfaas.domain.user.dto.request;

public record UserLoginRequest(
        String userId,
        String passWord
) {
}
