package com.connect.test2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuth2LoginConfig extends WebSecurityConfigurerAdapter {

    private final String clientId = "9ThSY82u8gIVmPlVJ075tL1J0fka";
    private final String clientSecret = "1otE7VkXQcANC_GZ1vFMFpMqqlYa";
    private final String rediredctUrl = "https://localhost:8081/connect"; //{baseUrl}/login/oauth2/code/{registrationId}";
    private final String authUrl = "https://localhost:9443/oauth2/authorize";
    private final String tokenUrl = "https://localhost:9443/oauth2/token";
    private final String jwkUrl = "https://localhost:9443/oauth2/jwks";
    private final String userIndoUrl = "https://localhost:9443/oauth2/userinfo";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                //.antMatchers("/connect").hasRole("ANONYMOUS")
                .and()
                .oauth2Login();
    }


    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(this.wsoClientRegistration());
    }

    private ClientRegistration wsoClientRegistration() {
        return ClientRegistration.withRegistrationId("wso")
                .clientId(clientId)
                .clientSecret(clientSecret)
                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")
                //.redirectUriTemplate(rediredctUrl)
                .scope("openid")
                .authorizationUri(authUrl)
                .tokenUri(tokenUrl)
                .userInfoUri(userIndoUrl)
                .userNameAttributeName(IdTokenClaimNames.SUB)
                .jwkSetUri(jwkUrl)
                .clientName("test")
                .build();
    }
}
