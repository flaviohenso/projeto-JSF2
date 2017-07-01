package com.flavio.service;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

@RequestScoped
public class ContextMensage {

	public ContextMensage(){		
	}
	
	public void addmsg(String id, Severity severity, String resumo, String detalhado) {
		FacesContext contexto = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(severity, resumo, detalhado);

		contexto.addMessage(id, msg);
	}
	
}
