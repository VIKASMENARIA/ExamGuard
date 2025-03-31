package com.example.registrationlogindemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RegistrationLoginDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistrationLoginDemoApplication.class, args);
	}

}
/*The main method is the standard entry point for a Java application. It uses SpringApplication.run to launch the Spring Boot application. 
 * This method bootstraps the application, starting the Spring context and the embedded web server (if applicable).
@SpringBootApplication is a convenience annotation in Spring Boot that combines three important annotations:
@Configuration: Indicates that the class can be used by the Spring IoC container as a source of bean definitions.
@EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
@ComponentScan: Tells Spring to scan the current package and its sub-packages for components, configurations, and services, allowing it to find and register beans within the application context.
By using @SpringBootApplication, you enable these three features with a single annotation, simplifying the configuration of your Spring Boot application.*/


