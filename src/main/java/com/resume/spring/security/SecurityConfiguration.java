package com.resume.spring.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.resume.repositories.UserRepository;
import com.resume.service.SSUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
 
        http
                .formLogin().failureUrl("/login?error")
                .defaultSuccessUrl("/")
                .loginPage("/login")
                .permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                .permitAll()
                .and().authorizeRequests()
				.antMatchers("/", "/addResume","/jobpost", "/resume/**","/logout",
						"/search/**", "/education/**", "/experience/**","/skill/**", "/job/**")
				.authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.inMemoryAuthentication().withUser("user").password("password").roles("Recruiter");
    	auth.userDetailsService(userDetailsServiceBean());
    }
    
    public UserDetailsService userDetailsServiceBean() throws Exception {
    	
    	return new SSUserDetailsService(userRepository);
    }
    
}