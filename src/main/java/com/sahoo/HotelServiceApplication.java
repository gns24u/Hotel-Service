package com.sahoo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;



@SpringBootApplication
//@EnableSwagger2
@EnableWebSecurity
//@EnableWebMvc
public class HotelServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(HotelServiceApplication.class, args);
		
	}
	

	
	
	
	
	

}
