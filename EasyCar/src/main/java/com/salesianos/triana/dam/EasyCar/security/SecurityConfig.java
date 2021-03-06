package com.salesianos.triana.dam.EasyCar.security;

import com.salesianos.triana.dam.EasyCar.security.jwt.JwtAccessDeniedHandler;
import com.salesianos.triana.dam.EasyCar.security.jwt.JwtAuthenticationEntryPoint;
import com.salesianos.triana.dam.EasyCar.security.jwt.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthenticationEntryPoint authenticacionEntryPoint;
    private final JwtAuthorizationFilter filter;
    private final JwtAccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(corsConfigurationSource());
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(authenticacionEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/register/**").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/login").anonymous()
                .antMatchers(HttpMethod.GET, "/me").authenticated()

                .antMatchers(HttpMethod.GET,"/profile/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/profile/**").authenticated()
                .antMatchers(HttpMethod.GET,"/usuario/**").authenticated()
                .antMatchers(HttpMethod.PUT,"/usuario/**").authenticated()
                .antMatchers(HttpMethod.DELETE,"/usuario/**").authenticated()

                .antMatchers(HttpMethod.GET, "/vehiculo/**").authenticated()
                .antMatchers(HttpMethod.POST, "/vehiculo/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/vehiculo/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/vehiculo/**").authenticated()

                .antMatchers(HttpMethod.GET, "/concesionario/**").authenticated()
                .antMatchers(HttpMethod.POST, "/concesionario/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/concesionario/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/concesionario/**").authenticated()

                .antMatchers(HttpMethod.GET, "/tipo/**").authenticated()
                .antMatchers(HttpMethod.POST, "/tipo/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/tipo/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/tipo/**").authenticated()

                .antMatchers(HttpMethod.GET, "/marca/**").authenticated()
                .antMatchers(HttpMethod.POST, "/marca/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/marca/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/marca/**").authenticated()

                .antMatchers(HttpMethod.GET, "/download/**").anonymous()

                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/error").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        http.headers().frameOptions().disable();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","DELETE","PUT"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}