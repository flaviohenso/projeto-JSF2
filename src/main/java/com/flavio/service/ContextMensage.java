package com.flavio.service;

import java.util.Iterator;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

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
//		FacesContext facesContext = FacesContext.getCurrentInstance();   
//        if (facesContext != null) {   
//        	if(!facesContext.getMessageList().isEmpty())
//        		facesContext.getMessageList().clear();    
//         }
		System.out.println("limpar msg...");
		  FacesContext facesContext = FacesContext.getCurrentInstance();
	       if (facesContext != null) {
	           Iterator iter = facesContext.getMessages();
	           while (iter.hasNext()) {
	               iter.remove();
	           }
	        }
	}

//	@Override
//	public void processAction(ActionEvent arg0) throws AbortProcessingException {
//		this.delMsg();
//		
//	}

}
