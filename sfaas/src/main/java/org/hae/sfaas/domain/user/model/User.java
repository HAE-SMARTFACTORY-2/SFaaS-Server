package org.hae.sfaas.domain.user.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Long userId;
    private Long factoryId;
    private String userEmail;
    private String userPwd;
    private String userName;
    private Long userMgr;
    private UserRole userRole;  // "ADMIN", "MANAGER", "USER"
    private String department;
    private String position;
    private LocalDateTime lastLoginAt;
}
