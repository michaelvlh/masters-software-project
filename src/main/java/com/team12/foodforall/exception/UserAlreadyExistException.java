package com.team12.foodforall.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author: Heng Gao
 * @date: 18/03/2022 :23:20
 **/
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserAlreadyExistException extends RuntimeException{
}
