package com.fort.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class SecurityConfiguration {

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
       UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
       CorsConfiguration corsConfiguration = new CorsConfiguration();
       corsConfiguration.addAllowedOrigin("http://localhost:4200");
       corsConfiguration.setAllowCredentials(true);
       corsConfiguration.setAllowedHeaders(Arrays.asList("X-Custom-Header", "Content-Type", "Authorization"));
       corsConfiguration.setAllowedMethods(Arrays.asList(
               HttpMethod.GET.name(),
               HttpMethod.HEAD.name(),
               HttpMethod.OPTIONS.name(),
               HttpMethod.POST.name(),
               HttpMethod.PUT.name(),
               HttpMethod.DELETE.name()));
       corsConfiguration.setMaxAge(1800L);
       source.registerCorsConfiguration("/client", corsConfiguration); // you restrict your path here
       source.registerCorsConfiguration("/counter", corsConfiguration); // you restrict your path here
       source.registerCorsConfiguration("/action", corsConfiguration); // you restrict your path here
       // System.out.println("CorsConfigurationSource");
       return source;
    }

    @Bean
    public CorsFilter corsFilter() {
       return new CorsFilter(corsConfigurationSource());
    }
}
