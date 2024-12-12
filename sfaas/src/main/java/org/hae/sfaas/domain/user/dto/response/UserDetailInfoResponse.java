package org.hae.sfaas.domain.user.dto.response;

import org.hae.sfaas.domain.user.model.DetailUser;
import org.hae.sfaas.domain.user.model.UserRole;

import java.time.LocalDateTime;

public record UserDetailInfoResponse(
        Long factoryId,
        String userEmail,
        String userName,
        Long userMgr,
        UserRole userRole,
        LocalDateTime lastLoginAt,
        String factoryCode,
        String factoryLocation
) {
    public static UserDetailInfoResponse of(DetailUser detailUser) {
        return new UserDetailInfoResponse(
                detailUser.getFactoryId(),
                detailUser.getUserEmail(),
                detailUser.getUserName(),
                detailUser.getUserMgr(),
                detailUser.getUserRole(),
                detailUser.getLastLoginAt(),
                detailUser.getFactoryCode(),
                detailUser.getFactoryLocation()
        );
    }
}
