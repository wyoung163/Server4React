package com.websocket.push.utils.keycloak;

import org.keycloak.OAuth2Constants;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfiguration {
    @Value("${keycloak.auth-server-url}")
    private String authServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    /*
     *  keycloak.json대신에 application.yml 파일에서 설정 값을 가져오게 설정
     */
    @Bean
    public KeycloakSpringBootConfigResolver KeycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    /*
     *  Keycloak 서버와 통신하기 위한 클라이언트 빌더
     * */
    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(authServerUrl)
                .realm(realm)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .build();
//      .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build()).build();

//     아이디/ 패스워드로 keycloak 접속 인증
//     KeycloakBuilder.builder()
//        .serverUrl(authServerUrl)
//        .realm(realm)
//        .clientId(clientId)
//        .grantType(OAuth2Constants.PASSWORD)
//        .clientSecret(clientSecret)
//        .username(username)
//        .password(password)
//        .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build()).build();
    }
}