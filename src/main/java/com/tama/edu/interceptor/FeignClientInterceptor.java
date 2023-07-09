package com.tama.edu.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class FeignClientInterceptor implements RequestInterceptor {

    //this will give the bearer token
    @Autowired
    private OAuth2AuthorizedClientManager manager;

    @Override
    public void apply(RequestTemplate template) {

        String token = manager
                .authorize(OAuth2AuthorizeRequest.withClientRegistrationId("my-internal-microservice-client").principal("internal")
                        .build())
                .getAccessToken()
                .getTokenValue();
        template.header("Authorization", "Bearer " + token);

    }
}