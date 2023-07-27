package com.team12.foodforall;

import com.team12.foodforall.domain.Project;
import com.team12.foodforall.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: Heng Gao
 * @date: 29/03/2022 17:07
 **/
@SpringBootTest
public class ProjectModelTest {

    @Test
    void TestProjectModel(){


        Project p = new Project();
        User user = new User();



        user.setFirstName("jack");
        user.setLastName("westwood");
        user.setId(123L);
        user.setEmail("user@user.com");
        user.setPassword("asdasdasd");

        p.setId(999L);
        p.setTitle("title");
        p.setContent("this is a good project");


        p.setUser(user);

        System.out.println(p);
    }
}
