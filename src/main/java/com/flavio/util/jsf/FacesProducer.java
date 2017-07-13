package com.flavio.util.jsf;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flavio.anotation.HttpServletRequestOutCDI;

/**
 * Classe que produz metodos injetáveis 
 */
public class FacesProducer {
	
	@Produces
	@RequestScoped
	public FacesContext getFacesContext(){
		return FacesContext.getCurrentInstance();
	}
	
	@Produces
	@RequestScoped
	public ExternalContext getExternalContext(){
		return getFacesContext().getExternalContext();
	}

	/*
	 * Esse método injetável esta anotado com um Qualifier @HttpServletRequestOutCDI
	 * para diferenciar do bean CDI HttpServletRequest
	 */
	@Produces
	@RequestScoped @HttpServletRequestOutCDI
	public HttpServletRequest getHttpServletRequest(){
		return (HttpServletRequest) getExternalContext().getRequest();
	}
	
	@Produces
	@RequestScoped
	public HttpServletResponse getHttpServletResponse(){
		return (HttpServletResponse) getExternalContext().getResponse();
	}
	
}
