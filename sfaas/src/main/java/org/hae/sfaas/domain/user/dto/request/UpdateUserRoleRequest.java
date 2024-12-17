package org.hae.sfaas.domain.user.dto.request;

import org.hae.sfaas.domain.user.model.UserRole;

public record UpdateUserRoleRequest(
        Long targetUserId,
        UserRole role
) {
}
