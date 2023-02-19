// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.azuresamples.msal4j.msidentityspringbootwebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MsIdentitySpringBootWebappApplication {
	public static void main(String[] args) {
		SpringApplication.run(MsIdentitySpringBootWebappApplication.class, args);
	}
	// @Override
 	// protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
 	// 	return builder.sources(MsIdentitySpringBootWebappApplication.class);
 	// }
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**").allowedOrigins("http://localhost:8082");
			}
		};
	}
}
