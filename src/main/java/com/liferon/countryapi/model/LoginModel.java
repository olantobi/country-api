package com.liferon.countryapi.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginModel {
    private String username;
    private String password;
}
