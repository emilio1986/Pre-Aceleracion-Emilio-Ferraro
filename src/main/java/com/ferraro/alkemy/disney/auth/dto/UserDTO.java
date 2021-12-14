package com.ferraro.alkemy.disney.auth.dto;


import lombok.Data;


@Data
public class UserDTO {
    @Email(message = "username must be an email")
    private String username;

    @Size(min = 8)
    private String password;
}

