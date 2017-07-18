package com.flavio.service;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

@RequestScoped
//public class ContextMensage implements ActionListener{
public class ContextMensage {

	public ContextMensage(){		
	}
	
	/*
	 * adiciona msg na fila do contexto do jsf
	 */
	public void addmsg(String id, Severity severity, String resumo, String detalhado) {
		FacesContext contexto = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(severity, resumo, detalhado);

		contexto.addMessage(id, msg);
	}
	
	/*
	 * remove msg da fila do contexto do jsf
	 */
	public void delMsg(){
		FacesContext facesContext = FacesContext.getCurrentInstance();   
        if (facesContext != null) {   
            facesContext.getMessageList().clear();    
         }
	}

//	@Override
//	public void processAction(ActionEvent arg0) throws AbortProcessingException {
//		this.delMsg();
//		
//	}

}
