package org.hae.sfaas.domain.user.dto.response;

import org.hae.sfaas.domain.user.model.User;
import org.hae.sfaas.domain.user.model.UserRole;

import java.time.LocalDateTime;

public record UserInfoResponse(
        Long userId,
        Long factoryId,
        String userEmail,
        String userName,
        Long userMgr,
        UserRole userRole,
        String department,
        String position,
        LocalDateTime lastLoginAt
) {
    public static UserInfoResponse of(User user) {
        return new UserInfoResponse(
                user.getUserId(),
                user.getFactoryId(),
                user.getUserEmail(),
                user.getUserName(),
                user.getUserMgr(),
                user.getUserRole(),
                user.getDepartment(),
                user.getPosition(),
                user.getLastLoginAt()
        );
    }
}
