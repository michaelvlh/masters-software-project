package com.team12.foodforall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author: Heng Gao
 * @date: 27/03/2022 00:37
 **/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
					.antMatchers("/dashboard-graphs").authenticated()
					.antMatchers("/dashboard-projects").authenticated()
					.anyRequest().permitAll()
					.and()
				.formLogin()
					.loginPage("/login")  // GET login
					.loginProcessingUrl("/login") // POST login
					.usernameParameter("email") // use email as key
					.defaultSuccessUrl("/") // on success
					.failureUrl("/login?loginError=true") // on error
					.permitAll()
					.and()
				.logout()
					.logoutSuccessUrl("/login") // back to login
					// .invalidateHttpSession(true)
					.deleteCookies("JSESSIONID")
					.permitAll()
					.and()
				.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
					.invalidSessionUrl("/login?error=INVALID_SESSION");

		http.csrf().disable();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
