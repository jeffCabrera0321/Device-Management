package com.Device.Manager.Device.Manager;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@SpringBootApplication(scanBasePackages = {"@Controllers","@Services", "@Models", "@Repositories"})
@SpringBootApplication
@ComponentScan(basePackages = {
    "com.Device.Manager.Device.Manager.Controllers",
    "com.Device.Manager.Device.Manager.Models",
    "com.Device.Manager.Device.Manager.Repositories",
    "com.Device.Manager.Device.Manager.Services"
})
public class DeviceManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeviceManagerApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry){
				WebMvcConfigurer.super.addCorsMappings(registry);
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:3000")
						.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE")
						.allowedHeaders("*")
						.allowCredentials(true);
			}
		};
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
