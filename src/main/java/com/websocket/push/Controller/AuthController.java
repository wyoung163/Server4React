package com.websocket.push.Controller;

import com.websocket.push.Dto.UserReq;
import com.websocket.push.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @RequestMapping(method = RequestMethod.POST, value = "/singup")
    public ResponseEntity signUp(@RequestBody UserReq userReq, HttpServletResponse response) {
        try {
//            if(authService.checkExistence(userReq.email)){
//                return ResponseEntity.ok("존재하는 사용자");
//            }
            UserReq result = authService.addUser(userReq);
            return ResponseEntity.ok(result);
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.ok("");
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity login(@RequestBody UserReq userReq) {
        try {
            AccessTokenResponse response = authService.setAuth(userReq);
            System.out.println(response);
            return ResponseEntity.ok(response);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
