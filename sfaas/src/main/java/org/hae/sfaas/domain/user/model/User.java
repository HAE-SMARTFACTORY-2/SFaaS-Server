package org.hae.sfaas.domain.user.model;

import lombok.Data;

@Data
public class User {
    private String userId;
    private String passWord;
    private String userName;
    private String FactoryCode;
    private String email;
    private String etc;

}
