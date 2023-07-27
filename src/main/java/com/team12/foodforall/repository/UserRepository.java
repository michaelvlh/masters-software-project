package com.team12.foodforall.repository;

import com.team12.foodforall.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Heng Gao
 * @date: 17/03/2022 :18:56
 **/

//CrudRepository is provided by framework, go into it to see default database operations.
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
