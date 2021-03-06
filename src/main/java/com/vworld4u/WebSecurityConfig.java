package com.vworld4u;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired AuthProvider authProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//        .logout().logoutUrl("/logout").invalidateHttpSession(true).logoutSuccessUrl("/login?logout").and()
        .authorizeRequests().antMatchers("/", "/register", "/hello", "/login", "/logout", "/verifyregistration/{emaildigest}/{vercode}").permitAll()
        .anyRequest().authenticated()
        .and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/home");
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authProvider);
    }
    
 }
