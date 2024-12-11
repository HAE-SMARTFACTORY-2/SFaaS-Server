package org.hae.sfaas.domain.user.dto.response;

import org.hae.sfaas.domain.user.model.User;

public record UserInfoResponse(
        String userName,
        String userId
) {
    public static UserInfoResponse of(User user) {
        return new UserInfoResponse(
                user.getUserName(),
                user.getUserId());
    }
}
