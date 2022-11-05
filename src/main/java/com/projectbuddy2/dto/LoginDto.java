package com.projectbuddy2.dto;

import lombok.Data;
/*
Transferobjekt für den Login
 */
@Data
public class LoginDto {
    private String usernameOrEmail;
    private String password;

    public LoginDto(String usernameOrEmail, String password){
        this.usernameOrEmail = usernameOrEmail;
        this.password = password;
    }
}
