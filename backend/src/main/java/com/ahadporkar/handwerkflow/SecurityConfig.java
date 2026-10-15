package com.ahadporkar.handwerkflow;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;
@Configuration public class SecurityConfig {
 @Bean PasswordEncoder encoder(){return new BCryptPasswordEncoder();}
 @Bean UserDetailsService users(@Value("${app.admin-user}") String username,@Value("${app.admin-password}") String password,PasswordEncoder encoder){if(password.isBlank())throw new IllegalStateException("ADMIN_PASSWORD must be supplied");return new InMemoryUserDetailsManager(User.withUsername(username).password(encoder.encode(password)).roles("ADMIN").build());}
 @Bean UrlBasedCorsConfigurationSource cors(@Value("${app.allowed-origin}") String origin){var config=new CorsConfiguration();config.setAllowedOrigins(List.of(origin));config.setAllowedMethods(List.of("GET","POST","PATCH","OPTIONS"));config.setAllowedHeaders(List.of("Authorization","Content-Type"));var source=new UrlBasedCorsConfigurationSource();source.registerCorsConfiguration("/api/**",config);return source;}
 @Bean SecurityFilterChain filterChain(HttpSecurity http) throws Exception {return http.cors(Customizer.withDefaults()).csrf(csrf->csrf.disable()).authorizeHttpRequests(auth->auth.requestMatchers("/actuator/health").permitAll().anyRequest().authenticated()).httpBasic(Customizer.withDefaults()).sessionManagement(s->s.sessionCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.STATELESS)).build();}
}
