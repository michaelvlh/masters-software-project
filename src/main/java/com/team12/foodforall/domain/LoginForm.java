package com.team12.foodforall.domain;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author: Heng Gao
 * @date: 19/03/2022 20:52
 **/
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class LoginForm {


    @Email
    @NotEmpty(message = "Email is mandatory")
    private String email;

    @Size(min = 8, message = "password should have at least 8 characters")
    @Size(max = 16, message = "password should have at most 16 characters")
    @NotEmpty(message = "Password is mandatory")
    private String password;
}
