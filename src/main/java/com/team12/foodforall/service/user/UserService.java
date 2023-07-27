package com.team12.foodforall.service.user;

import com.team12.foodforall.domain.RegisterForm;
import com.team12.foodforall.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author: Heng Gao
 * @date: 17/03/2022 :22:25
 **/
@Service
public interface UserService {

    User registerUser(RegisterForm user);

    Optional<User> findUserById(Long id);

    List<User> findAllUsers();

    void deleteUserById(long id);

    User findUserByEmail(String email);

}
