package com.team12.foodforall.service.user;

import com.team12.foodforall.domain.RegisterForm;
import com.team12.foodforall.domain.User;
import com.team12.foodforall.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author: Heng Gao
 * @date: 18/03/2022 :19:25
 **/
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public void deleteUserById(long id) {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
        }
    }

    @Override
    public User registerUser(RegisterForm registerForm){

        // Duplicate Account Validation
        User queryResult = userRepository.findByEmail(registerForm.getEmail());
        if(queryResult != null){
            //TODO: replace exception
            throw new RuntimeException("account already exists");
        }
        // TODO: more validation


        if(!registerForm.getPassword().equals(registerForm.getConfirmedPassword())){
            throw new RuntimeException("Password not matched");
        }

        //TODO: Encrypt Password
        String encryptedPassword = encoder.encode(registerForm.getPassword());

        User user = new User();
        user.setFirstName(registerForm.getFirstName());
        user.setLastName(registerForm.getLastName());
        user.setEmail(registerForm.getEmail());
        user.setPassword(encryptedPassword);
        user.setCharityName(registerForm.getCharityName());

        return userRepository.save(user);
    }



    @Override
    public Optional<User> findUserById(Long id) { return userRepository.findById(id);}

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public ArrayList<User> findAllUsers() {
        return (ArrayList<User>) userRepository.findAll();
    }
}
