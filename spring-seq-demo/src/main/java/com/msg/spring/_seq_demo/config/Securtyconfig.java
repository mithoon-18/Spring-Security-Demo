package com.msg.spring._seq_demo.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Securtyconfig {

	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		//provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		return provider;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(customizer -> customizer.disable())
				.authorizeHttpRequests(request -> request.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return http.build();
		
	
		
		/*
		 * Customizer<CsrfConfigurer<HttpSecurity>> custCsrf = new
		 * Customizer<CsrfConfigurer<HttpSecurity>>() {
		 * 
		 * @Override public void customize(CsrfConfigurer<HttpSecurity> configurer) {
		 * 
		 * configurer.disable(); } };
		 * 
		 * Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.
		 * AuthorizationManagerRequestMatcherRegistry> custHttp = new
		 * Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.
		 * AuthorizationManagerRequestMatcherRegistry>() {
		 * 
		 * @Override public void customize(
		 * AuthorizeHttpRequestsConfigurer<HttpSecurity>.
		 * AuthorizationManagerRequestMatcherRegistry registry) {
		 * registry.anyRequest().authenticated();
		 * 
		 * } };
		 * 
		 * http.authorizeHttpRequests(custHttp); http.csrf(custCsrf);
		 */
	}
}
