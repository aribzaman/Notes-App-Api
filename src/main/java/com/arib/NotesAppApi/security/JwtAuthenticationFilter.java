package com.arib.NotesAppApi.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//har baar chalega, har request par
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);

	@Autowired
	private JwtHelper jwtHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

		// requestHeader in format---> //Bearer 2352345235sdfrsfgsdfsdf
		String requestHeader = request.getHeader("Authorization");
//		logger.info(" Header :  {}", requestHeader);

		String token = null;
		String username = null; //Username present within token

		//For checking correct syntax of Header
		//header has something and it is starting with Bearer
		if (requestHeader != null && requestHeader.startsWith("Bearer")) {

			// "Bearer " ko clip kardege
			token = requestHeader.substring(7);

			try {
				username = this.jwtHelper.getUsernameFromToken(token);

			} catch (IllegalArgumentException e) {
				logger.info("Illegal Argument while fetching the username !!");
				e.printStackTrace();
			} catch (ExpiredJwtException e) {
				logger.info("Given jwt token is expired !!");
				e.printStackTrace();
			} catch (MalformedJwtException e) {
				logger.info("Some changed has done in token !! Invalid Token");
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		else {
//			logger.info(requestHeader, token, username);
			System.out.println(requestHeader);
			System.out.println(token);
			System.out.println(username);
			logger.info("Invalid Header Value !! ");
		}

		//Going for Authentication of user+token if above went successful
		//username bhara ho aur SecurityContextHolder already bhara nahi hona chahiye
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			// fetch user detail from username
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

			//helper token aur user ko validate karega aur boolean return karega
			Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);

			if (validateToken) {
				//create authentication with current userDetails and its authorities
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				// security context me se context nikaala hai then set the authentication
				//SPRING SECURITY ki ghnati bajjj jaayegi ki authenticate hogaya
				SecurityContextHolder.getContext().setAuthentication(authentication);

			} else {
				logger.info("Validation fails !!");
			}

		}

		//taken as parameter in filter function initially and is used to forwarded next request
		filterChain.doFilter(request, response);

	}
}