package org.hae.sfaas.domain.user.dto.response;

import org.hae.sfaas.domain.user.model.DetailUser;
import org.hae.sfaas.domain.user.model.UserRole;

import java.time.LocalDateTime;

public record UserDetailInfoResponse(
        Long userId,
        Long factoryId,
        String userEmail,
        String userName,
        Long userMgr,
        UserRole userRole,
        String department,
        String position,
        LocalDateTime lastLoginAt,
        String factoryCode,
        String factoryLocation
) {
    public static UserDetailInfoResponse of(DetailUser detailUser) {
        return new UserDetailInfoResponse(
                detailUser.getUserId(),
                detailUser.getFactoryId(),
                detailUser.getUserEmail(),
                detailUser.getUserName(),
                detailUser.getUserMgr(),
                detailUser.getUserRole(),
                detailUser.getDepartment(),
                detailUser.getPosition(),
                detailUser.getLastLoginAt(),
                detailUser.getFactoryCode(),
                detailUser.getFactoryLocation()
        );
    }
}
