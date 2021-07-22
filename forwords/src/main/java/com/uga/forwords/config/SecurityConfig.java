package com.uga.forwords.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("AuthUserDetailsServiceImpl")
	private UserDetailsService userDetailsService;
	
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		 .antMatchers("/home").hasRole("CUSTOMER") // allow public access to landing page
		 .antMatchers("/leaders/**").hasRole("CUSTOMER")
		 .antMatchers("/systems/**").hasRole("ADMIN")
		 .anyRequest().permitAll()
		 .and()
			.formLogin()
			.loginPage("/showMyLoginPage")
			.loginProcessingUrl("/authenticateTheUser")
			.permitAll()
		 .and()
		 	.logout().permitAll()
		 	//.logoutSuccessUrl("/")
		 	//.permitAll()
		 .and()
		 	.exceptionHandling()
		 	.accessDeniedPage("/access-denied");
		
		
		/*
		 * The .logout() will add a spring security logout support that will be exposed
		 * at default url- "context-root(application root path)/logout". So to logout from app(and redirect to loginPage), we need send data(or hit) to this default url on click of logout button 
		 */
		
		
	}
	
	// Beans
	// bcrypt bean definition
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// authenticationProvider bean definition
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userDetailsService); // set the custom user details service
		auth.setPasswordEncoder(passwordEncoder()); // set the password encoder - bcrypt
		return auth;
	}

}
