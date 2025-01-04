package br.com.gabrielferreira.usuarios.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final AppAuthenticationProvider appAuthenticationProvider;

    private final JWTValidatorTokenFilter jwtValidatorTokenFilter;

    private final UnauthorizedHandler unauthorizedHandler;

    private final ForbiddenHandler forbiddenHandler;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/**.html"), new AntPathRequestMatcher("/**.css")
                , new AntPathRequestMatcher("/**.js"), new AntPathRequestMatcher("/v3/api-docs/**"), new AntPathRequestMatcher("/swagger-ui/**")
                , new AntPathRequestMatcher("/swagger-ui.html")));
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtValidatorTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(appAuthenticationProvider)
                .authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.POST, "/login/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/v1/generos/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/v1/tipos-telefones/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/v1/perfis/**").permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(eh -> eh.authenticationEntryPoint(unauthorizedHandler)
                        .accessDeniedHandler(forbiddenHandler))
                .build();
    }
}
