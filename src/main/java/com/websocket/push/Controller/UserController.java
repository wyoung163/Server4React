package com.websocket.push.Controller;

import com.websocket.push.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/user")
    public ResponseEntity getUser(@RequestHeader Map<String, String> data){
        Map<String, Object> result = userService.getAccessToken(data.get("refresh_token"), data.get("access_token"));
        Map<String, String> user = new HashMap<>();
        user.put("email", (String) result.get("username"));
        return ResponseEntity.ok(user);
    }
}
