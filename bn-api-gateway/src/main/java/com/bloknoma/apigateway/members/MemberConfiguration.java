package com.bloknoma.apigateway.members;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MemberDestinations.class)
public class MemberConfiguration {

    // members 요청 라우팅
    @Bean
    public RouteLocator memberProxyRouting(RouteLocatorBuilder builder, MemberDestinations memberDestinations) {
        return builder.routes()
                .route(r -> r.path("/members").and().method("POST").uri(memberDestinations.getMemberServiceUrl()))
                .route(r -> r.path("/members").and().method("PUT").uri(memberDestinations.getMemberServiceUrl()))
                .build();
    }
}
