package com.bytesville.microservices.demo.springcloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.Routes;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

import static org.springframework.cloud.gateway.filter.factory.GatewayFilters.addResponseHeader;
import static org.springframework.cloud.gateway.handler.predicate.RoutePredicates.host;
import static org.springframework.cloud.gateway.handler.predicate.RoutePredicates.path;


@SpringBootApplication
public class SpringcloudgatewayApplication {

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.host("**.abc.org").and().path("/image/png")
						.addResponseHeader("X-TestHeader", "foobar")
						.uri("http://httpbin.org:80")
				)
				.route(r -> r.path("/image/webp")
						.addResponseHeader("X-AnotherHeader", "baz")
						.uri("http://httpbin.org:80")
				)
				.route(r -> r.path("/getip")
							.uri("http://httpbin.org:80/ip")
				)
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudgatewayApplication.class, args);
	}
}
