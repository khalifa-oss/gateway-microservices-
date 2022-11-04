package com.id.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableHystrix
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run( GatewayApplication.class, args );
    }

    //create a static locator route in the gateway "localisateur d'iterinaire"
//    @Bean
//    RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route( "r1", r -> r.path( "/customer/**" ).uri( "http://localhost:8080" ) )
//                .route( "r2", r -> r.path( "/product/**" ).uri( "http://localhost:8082" ) )
//                .build();
//
//    }
    //create a static  locator route in the gateway with  eureka (discovery service) with load balancer(lb) "localisateur d'iterinaire"

    //    @Bean
//    RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route( "r1", r -> r.path( "/customer/**" ).uri( "lb://CUSTOMER-SERVICE" ) )
//                .route( "r2", r -> r.path( "/product/**" ).uri( "lb://PRODUCT-SERVICE" ) )
//                .build();
//    }
    @Bean
    public RouteLocator findRoutes(RouteLocatorBuilder builder) {
        return builder.routes().route( "id1", route -> route.path( "/public/**" )
                        .uri( "http://localhost:8080" ) )
//                .route( "id2", r2 -> r2.path( "/forecast/**" )
//                        .filters( f ->
//                                f.addRequestHeader( "X-RapidAPI-Key", "fcf3011dd4msh0217be77c33d6cbp138f87jsn4602ee097f15" )
//                                        .addRequestHeader( "X-RapidAPI-Host", "weatherbit-v1-mashape.p.rapidapi.com" )
//                                        .circuitBreaker( c->c.setName( "myCircuitBreaker" ).setFallbackUri( "forward:/inCaseOfFailure" ) )
//                        ).uri( "https://weatherbit-v1-mashape.p.rapidapi.com/forecast" ) )
                .build();


    }

    //create a dynamic routes in the gateway with eureka
    @Bean
    DiscoveryClientRouteDefinitionLocator dynamicRoutes(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp) {
        return new DiscoveryClientRouteDefinitionLocator( rdc, dlp );
    }


}
