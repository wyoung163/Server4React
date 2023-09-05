package com.websocket.push.Dao;

import com.websocket.push.Dto.UserReq;
import com.websocket.push.Entity.User;
import com.websocket.push.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AuthDao {
    private final UserRepository userRepository;
    public void insertUser(UserReq userReq){
        User user = new User(userReq.email, userReq.password);
        userRepository.save(user);
    }
}
