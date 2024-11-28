package com.example.readRent.Dto;

import com.example.readRent.Entities.Role;

import lombok.Data;

@Data
public class SignUpRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;
}
