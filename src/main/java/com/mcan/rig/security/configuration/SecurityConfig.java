package com.mcan.rig.security.configuration;

import com.mcan.rig.properties.JwtProperties;
import com.mcan.rig.security.jwt.JwtAuthorizationFilter;
import com.mcan.rig.service.CustomerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomerService customerService;

    private final JwtProperties jwtProperties;

    public SecurityConfig(CustomerService customerService, JwtProperties jwtProperties) {
        this.customerService = customerService;
        this.jwtProperties = jwtProperties;
    }

    @Override
    protected void configure (HttpSecurity http) throws Exception {
        // for dev purposes
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .headers().frameOptions().disable()
                .and()
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), customerService, jwtProperties))
                .authorizeRequests()
                .antMatchers("/api/v1/book/**").hasAuthority("customer")
                .antMatchers(HttpMethod.GET,"/api/v1/customer/**").hasAuthority("customer")
                .antMatchers(HttpMethod.POST, "/api/v1/order/**").hasAuthority("customer")
                .antMatchers("/api/v1/statistic/**").hasAuthority("customer")
                .anyRequest().permitAll();
    }

    @Bean
    public InternalResourceViewResolver defaultViewResolver () {
        return new InternalResourceViewResolver();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean () throws Exception {
        return super.authenticationManagerBean();
    }
}

