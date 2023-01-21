package com.mikhaildolgopolov.spring;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
		(exclude = {FlywayAutoConfiguration.class,
		UserDetailsServiceAutoConfiguration.class})
public class AdventuresApplication {
	public static void main(String[] args) {
		SpringApplication.run(AdventuresApplication.class, args);
	}

}
