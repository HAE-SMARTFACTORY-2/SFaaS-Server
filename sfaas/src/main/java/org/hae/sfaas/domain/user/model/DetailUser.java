package org.hae.sfaas.domain.user.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DetailUser {
    private Long userId;
    private Long factoryId;
    private String userEmail;
    private String userPwd;
    private String userName;
    private Long userMgr;
    private String department;
    private String position;
    private UserRole userRole;  // "ADMIN", "MANAGER", "USER"
    private LocalDateTime lastLoginAt;
    private String factoryCode;
    private String factoryLocation;
}
