package com.finki.wp.ugostitelskiobjekti.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //metodot vo koj kje ja pravime celata implementacija
    private final PasswordEncoder passwordEncoder;
    private final CustomUsernamePasswordAuthenticationProvider customUsernamePasswordAuthenticationProvider;

    public WebSecurityConfig(PasswordEncoder passwordEncoder, CustomUsernamePasswordAuthenticationProvider customUsernamePasswordAuthenticationProvider) {
        this.passwordEncoder = passwordEncoder;
        this.customUsernamePasswordAuthenticationProvider = customUsernamePasswordAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //tuka pravime bazichna konfiguracija
        // da ne se ovozmozhi povoci od third party app
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/home","/assets/**","/register").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
              //  .antMatchers("/employee/**").hasRole("EMPLOYEE")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .failureUrl("/login?error=BadCredentials")
                .defaultSuccessUrl("/home",true)
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access_denied");


    }

    //OVA E ZA CUSTOM AUTHENTICATION
        @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//CustomUsernamePasswordAuthenticationProvider- da go postavime vo konfiguracijata na spring security
       //zatoa dodadovme zavisnost kon istoto i so toa sme napravile custom authentication
        auth.authenticationProvider(customUsernamePasswordAuthenticationProvider);

    }

}
