package io.jctiru.springbootmicroservicessandboxuserservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment env;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/**").hasIpAddress(env.getProperty("gateway.ip"))
				.anyRequest().authenticated()
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.headers().frameOptions().disable();
	}

}
