package com.websocket.push.Service;

import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    @Value("${keycloak.auth-server-url}")
    private String authServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    private final Keycloak keycloak;

    /**
       액세스 토큰 활용해서 사용자 이름 반환
     */
    public Map<String, Object> getAccessToken(String refresh_token, String access_token) {
        //AccessTokenResponse response2 = getAccessWithRefresh(refresh_token);

        String url2 = authServerUrl + "/realms/" + realm + "/protocol/openid-connect/userinfo";

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<?> entity = new HttpEntity<>(map, headers);

        Map<String, Object> data = new HashMap<String, Object>();
//        headers.add( "Authorization","Bearer " + response.getToken());
        headers.add( "Authorization","Bearer " + access_token);

        ResponseEntity<Map> response2 = restTemplate.exchange( url2, HttpMethod.POST, entity, Map.class );

        data.put("username", response2.getBody().get("preferred_username"));
        return data;
    }

    /**
     * 리프레쉬 토큰 활용해서 액세스 토큰 재발급
     */
    public AccessTokenResponse getAccessWithRefresh(String refresh_token){
        Map<String, Object> clientCredentials = new HashMap<>();
        clientCredentials.put("refresh_token", refresh_token);
        clientCredentials.put("secret", clientSecret);
        clientCredentials.put("grant_type", "refresh_token");

        Configuration configuration =
                new Configuration(authServerUrl, realm, clientId, clientCredentials, null);
        AuthzClient authzClient = AuthzClient.create(configuration);
        AccessTokenResponse response =
                authzClient.obtainAccessToken();

        return response;
    }
}
