package com.flavio.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flavio.anotation.HttpServletRequestOutCDI;

@Named
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;

	@Inject
	private FacesContext context;

	@Inject @HttpServletRequestOutCDI
	private HttpServletRequest httpServletRequest;

	@Inject
	private HttpServletResponse httpServletResponse;

	public void login() throws ServletException, IOException {
		RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/j_spring_security_check");
		/*
		 * dispacha a requisição para o spring security
		 */
		dispatcher.forward(httpServletRequest, httpServletResponse);

		/*
		 * context.responseComplete(); : encerrar o processamento do JSF
		 */
		context.responseComplete();

		System.out.println("login");
	}

	public void logout() throws ServletException, IOException {
		RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/j_spring_security_logout");
		/*
		 * dispacha a requisição para o spring security
		 */
		dispatcher.forward(httpServletRequest, httpServletResponse);

		/*
		 * context.responseComplete(); : encerrar o processamento do JSF
		 */
		context.responseComplete();

		System.out.println("logout");
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
