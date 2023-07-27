package com.team12.foodforall.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author: Heng Gao
 * @date: 08/03/2022 :17:35
 **/
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class RegisterForm {

    private Long id;

    @Size(min = 2, message = "user name should have at least 2 characters")
    @NotEmpty(message = "firstname is mandatory")
    private String firstName;

    @Size(min = 2, message = "user name should have at least 2 characters")
    @NotEmpty(message = "lastname is mandatory")
    private String lastName;

    @Size(min = 1, max = 18, message = "Charity name has length between [1,18]")
    @NotEmpty(message = "Charity Name is mandatory")
    private String charityName;

    @Email
    @NotEmpty(message = "Email is mandatory")
    private String email;


    @Size(min = 8, message = "password should have at least 8 characters")
    @Size(max = 16, message = "password should have at most 16 characters")
    @NotEmpty(message = "Password is mandatory")
    private String password;


    @Size(min = 8, message = "confirmedPassword should match password")
    @Size(max = 16, message = "confirmedPassword should match password")
    @NotEmpty(message = "ConfirmedPassword is mandatory")
    private String confirmedPassword;
}
