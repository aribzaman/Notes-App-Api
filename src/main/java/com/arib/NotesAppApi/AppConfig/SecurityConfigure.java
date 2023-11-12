package com.arib.NotesAppApi.AppConfig;

import com.arib.NotesAppApi.security.JwtAuthenticationEntryPoint;
import com.arib.NotesAppApi.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfigure {

    private final JwtAuthenticationEntryPoint point;
    private final JwtAuthenticationFilter filter;
	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;

public SecurityConfigure(JwtAuthenticationEntryPoint point, JwtAuthenticationFilter filter,
			UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
		super();
		this.point = point;
		this.filter = filter;
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//    	.authorizeHttpRequests(auth -> ...): This is where you specify authorization rules for different HTTP requests. The rules defined here are as follows:
//    	Requests to paths starting with "/home/**" must be authenticated.
//    	Requests to "/auth/login" are permitted without authentication.
//    	All other requests must be authenticated.

        http.csrf(csrf -> csrf.disable())
        		.cors(cors-> cors.disable())
        		.authorizeHttpRequests(auth->auth.requestMatchers("/home/**")
        				.authenticated()
        				.requestMatchers("/auth/login")
        				.permitAll()
						.requestMatchers("/user/login")
						.permitAll()
						.requestMatchers("/user")
						.permitAll()
        				.requestMatchers("/auth/create-user")
        				.permitAll()
        				.anyRequest()
        				.authenticated())
//                .and()
                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

//Database ke liye DAO Authentication provider
@Bean
public DaoAuthenticationProvider daoDauAuthenticationProvider()
{
	DaoAuthenticationProvider dauAuthenticationProvider=new DaoAuthenticationProvider();
	dauAuthenticationProvider.setUserDetailsService(userDetailsService);
	dauAuthenticationProvider.setPasswordEncoder(passwordEncoder);
	return dauAuthenticationProvider;
}

}