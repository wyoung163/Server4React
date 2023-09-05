package com.websocket.push.Service;

import com.websocket.push.Dao.AuthDao;
import com.websocket.push.Dto.UserReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthDao authDao;

    public void addUser(UserReq userReq){
        authDao.insertUser(userReq);
    }
}
