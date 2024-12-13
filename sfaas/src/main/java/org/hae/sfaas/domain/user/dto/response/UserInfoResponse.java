package org.hae.sfaas.domain.user.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import org.hae.sfaas.domain.user.model.User;
import org.hae.sfaas.domain.user.model.UserRole;

import java.time.LocalDateTime;

public record UserInfoResponse(
        Long factoryId,
        String userEmail,
        String userName,
        Long userMgr,
        UserRole userRole,
        LocalDateTime lastLoginAt
) {
    public static UserInfoResponse of(User user) {
        return new UserInfoResponse(
                user.getFactoryId(),
                user.getUserEmail(),
                user.getUserName(),
                user.getUserMgr(),
                user.getUserRole(),
                user.getLastLoginAt()
        );
    }
}
