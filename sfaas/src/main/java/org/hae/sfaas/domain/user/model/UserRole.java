package org.hae.sfaas.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum UserRole {
    USER("USER"),
    ADMIN("ADMIN"),
    MANAGER("MANAGER");

    private String role;
}
