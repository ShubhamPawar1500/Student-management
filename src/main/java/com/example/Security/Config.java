package com.example.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.Service.StudentService;


@EnableWebSecurity
public class Config extends WebSecurityConfigurerAdapter{
	
	@Autowired
	StudentService s;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("shubham").password(encoder().encode("abc")).roles("ADMIN","USER");
//		auth.inMemoryAuthentication().withUser("gaurav").password(encoder().encode("def")).roles("USER");
		
		auth.userDetailsService(s);
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/student/admin").hasAnyRole("ADMIN")
		.antMatchers("/student/user").hasAnyRole("ADMIN", "USER").anyRequest().authenticated().and().httpBasic();
		
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/student/get");
	}


	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}


}
