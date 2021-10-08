package com.codercampus.assignment14.repository;

import com.codercampus.assignment14.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {

    private Long userId = 0L;

    private Map<Long, User> users = new HashMap<>();

    public User saveUser(User user){
        user.setId(++userId);
        this.users.put(userId, user);
        return user;
    }


}
