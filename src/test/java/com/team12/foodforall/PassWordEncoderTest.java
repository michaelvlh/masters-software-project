package com.team12.foodforall;

import com.team12.foodforall.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author: Heng Gao
 * @date: 27/03/2022 12:24
 **/
@SpringBootTest
public class PassWordEncoderTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Test
    public void TestBCryptPasswordEncoder() {

        // simple demo
        String encode = encoder.encode("12345678");
        System.out.println("result of encoding '12345678' is : " + encode); //$2a$10$fUO/XFbOWa/BQKw8FQciuOVj7f41FeOG0zfVuIpQfLGUFP.qQ2MQO

        // test encode
        String encode1 = encoder.encode("12345678");
        String encode2 = encoder.encode("12345678");
        System.out.println("encode1 of encoding '12345678': " + encode1);
        System.out.println("encode2 of encoding '12345678 : " + encode2);
        System.out.println(assertThat(encode1).isNotEqualTo(encode2)); // 2 encode should be different

        // test encode/decode
        final String password3 = "12345678";
        String encode3 = encoder.encode(password3);
        boolean matche_result = encoder.matches(password3, encode3);
        assertThat(matche_result).isTrue();
    }

    @Test
    void we(){
        assertThat(encoder.matches("12345678", "$2a$10$vO.nDcuMoqQJL/QszPS3BeXydl9VKmPGWGgfNjn6AOk9saHE0vtpm")).isTrue();
    }
}
