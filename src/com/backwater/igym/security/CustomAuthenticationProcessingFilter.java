package com.backwater.igym.security;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CustomAuthenticationProcessingFilter extends UsernamePasswordAuthenticationFilter {

	@Override
	protected void successfulAuthentication(final HttpServletRequest request, final HttpServletResponse response,
			final Authentication authResult) throws IOException, ServletException {
		final Writer out = response.getWriter();
		out.write("{success:true, targetUrl : ''}");
		out.flush();
		super.successfulAuthentication(request, response, authResult);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		final Writer out = response.getWriter();
		out.write("{ success: false, errors: { reason: 'Login failed. Try again.' }}");
		out.flush();
		super.unsuccessfulAuthentication(request, response, failed);
	}
}
