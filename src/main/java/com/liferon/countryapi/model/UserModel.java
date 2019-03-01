package com.liferon.countryapi.model;

import com.liferon.countryapi.annotations.ExtendedEmailValidator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
public class UserModel {
    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Date of birth is required")
    @Pattern(regexp = "\\d\\d[-]\\d\\d[-]\\d\\d\\d\\d", message="Invalid date format")
    private String dateOfBirth;

    @NotBlank(message = "Email is required")
    @ExtendedEmailValidator(message="Not a valid email address")
    private String email;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;
}
