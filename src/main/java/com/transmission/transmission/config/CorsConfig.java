package com.transmission.transmission.config;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author ydw
 * @date 2019年4月15日
 */
@Configuration
public class CorsConfig {
	
	
	
    private CorsConfiguration buildConfig() {
    	List<String> allowedHeaders = new ArrayList<String>();
    	List<String> allowedMethods = new ArrayList<String>();
		allowedHeaders.add("Authorization");
		allowedHeaders.add("Content-Type");
		
		allowedMethods.add("POST");
		allowedMethods.add("GET");
		allowedMethods.add("OPTIONS");
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); 
        corsConfiguration.setAllowedHeaders(allowedHeaders);
        corsConfiguration.setAllowCredentials(Boolean.TRUE);
        corsConfiguration.setAllowedMethods(allowedMethods);
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }
    
     @Bean
    public RestTemplate restTemplate() {
    	RestTemplate restTemplate = new RestTemplate();
    	restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }
}