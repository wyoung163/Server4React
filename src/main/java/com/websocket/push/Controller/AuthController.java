package com.websocket.push.Controller;

import com.websocket.push.Dto.UserReq;
import com.websocket.push.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public ResponseEntity login(@RequestBody UserReq userReq, HttpServletResponse response) {
        try {
            System.out.println(userReq);
            authService.addUser(userReq);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public ResponseEntity login() {
        try {
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
