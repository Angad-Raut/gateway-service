package com.projectx.gateway_service.filterconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Autowired
    RequestAuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("authentication-service", r -> r.path("/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://AUTHENTICATION-SERVICE"))
                .route("company-service", r -> r.path("/companyDetails/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://COMPANY-SERVICE"))
                .route("document-service", r -> r.path("/documents/**","/companyDocuments/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://DOCUMENT-SERVICE"))
                .route("expense-service", r -> r.path("/expenses/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://EXPENSE-SERVICE"))
                .route("income-service", r -> r.path("/incomes/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://INCOME-SERVICE"))
                .route("report-service", r -> r.path("/reports/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://REPORT-SERVICE"))
                .route("resume-service",r -> r.path("/educationDetails/**",
                                "/employeeDetails/**","/projectDetails/**",
                                "/technoStackDetails/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://RESUME-SERVICE"))
                .build();
    }
}
