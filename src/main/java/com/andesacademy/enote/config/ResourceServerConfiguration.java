/**
 * 
 */
package com.andesacademy.enote.config;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import com.andesacademy.enote.ErrorNoteBackendApplication;

/**
 * @author a2g
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends
ResourceServerConfigurerAdapter {
	
	public void configure(HttpSecurity http) throws Exception {
		Logger.getLogger(ErrorNoteBackendApplication.class.getName()).log(Level.INFO, "Inicializando acceso");
		http.anonymous().and().authorizeRequests()
			.antMatchers(HttpMethod.POST, "/users")
			.permitAll()
			.antMatchers(HttpMethod.GET, "/users/validate/**")
			.permitAll()
			.antMatchers("/**")
			.authenticated();
	}
}
