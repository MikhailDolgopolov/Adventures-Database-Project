package com.mikhaildolgopolov.spring;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//		(exclude = {FlywayAutoConfiguration.class,
//		UserDetailsServiceAutoConfiguration.class})
public class AdventuresApplication {
	public static void main(String[] args) {
		SpringApplication.run(AdventuresApplication.class, args);
	}

}
