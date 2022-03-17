package com.cap.pocvng.config;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Bean required for WebSecurityConfig. In seperate class because circular dependence
 * error since spring boot 2.6.1.
 */
@Configuration
public class KeyCloackConfigResolverBean {
    @Bean
    public KeycloakSpringBootConfigResolver KeycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }
}
