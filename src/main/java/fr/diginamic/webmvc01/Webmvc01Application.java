package fr.diginamic.webmvc01;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpSession;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "http://localhost:4200",
		allowedHeaders = {"Requestor-Type", "Authorization", "X-Auth-Token"},
		exposedHeaders = "X-Get-Header")
public class Webmvc01Application implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(Webmvc01Application.class, args);
	}
	/**
	 * Configuration pour le chargement des 
	 * messages Intenationaux
	 * messages.properties
	 * @return
	 */
	@Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = 
        		new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
	
	@GetMapping("/token")
	public Map<String, String> token(HttpSession session) {
		return Collections.singletonMap("token", session.getId());
	}
	
}
