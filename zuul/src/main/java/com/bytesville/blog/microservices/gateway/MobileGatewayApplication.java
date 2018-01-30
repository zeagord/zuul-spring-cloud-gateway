package com.bytesville.blog.microservices.gateway;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableZuulProxy			                // Using Zuul Proxy for providing additional filters for routing
@EnableCircuitBreaker                       // Enabling the Circuit breaker to handle the failures in a graceful way
@RestController
@EnableScheduling
public class MobileGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileGatewayApplication.class, args);
	}

	@Bean
	public AddResponseHeaderFilter addResponseHeaderFilter(){
	    return new AddResponseHeaderFilter();
    }

    @Bean
    public ModifyResponseBodyFilter modifyResponseBodyFilter(){
	    return new ModifyResponseBodyFilter();
    }

    @Bean
    public VerifyRequestHeaderFilter verifyRequestHeaderFilter() {
	    return new VerifyRequestHeaderFilter();
    }

    @Bean
    ErrorProcessingFilter errorProcessingFilter() {
	    return new ErrorProcessingFilter();
    }

    @RequestMapping("/timeout")
    public String timeout() throws InterruptedException {
        Thread.sleep(15000);
        return "timeout";
    }

    @RequestMapping("/hello")
    public String zuulwork() {
        String greeting = String.format("{\"status\": \"success\", \"code\": 2000}");
        return greeting;
    }

    @Bean
    public HystrixCommandAspect hystrixAspect(){
        return new HystrixCommandAspect();
    }}
