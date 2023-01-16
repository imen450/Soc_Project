package com.example.GatewayService;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}
	
	@Bean
	DiscoveryClientRouteDefinitionLocator dynamicRoutes(ReactiveDiscoveryClient
	rdc,
	DiscoveryLocatorProperties dlp){
	return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
	}
	@Bean
	RouteLocator gatewayRoutes(RouteLocatorBuilder builder){

		return builder.routes()
				.route( r->r.path("/customers/**").uri("lb://CUSTOMER-SERVICE")) 
				.route( r->r.path("/products/**").uri("lb://INVENTORY-SERVICE")) 
				.build();
	}

}

