package org.hae.sfaas.domain.user.dto.request;

public record UserLoginRequest(
        String userEmail,
        String userPwd
) {
}
