package com.enrikraymtz.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import com.enrikraymtz.springboot.configuration.JpaConfiguration;

@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.enrikraymtz.springboot"})
@EnableConfigurationProperties

public class SpringBootCRUDApp {
	
	public static void main(String[] args){
		SpringApplication.run(SpringBootCRUDApp.class, args);
	}
}
