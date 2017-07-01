/**Autor: Flávio Henrique
 * 8 de abr de 2017ProjetoBootStrap2
 */
package com.flavio.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.flavio.anotation.Email;
import com.flavio.model.Registro;
import com.flavio.service.Mensagem;

/**
 * @author root
 *
 */
@Named
@RequestScoped
public class VendaBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Inject @Email
	private Mensagem mensagem;
	
	private List<Registro> registros;
	private Registro registro;
	
	@PostConstruct
	private void init(){
		registros = new ArrayList<Registro>();
		registro = new Registro();
	}
	
	
	public String salvarResgitro(){
		mensagem.enviar();
		return "sucesso";
	}
	
	
	public List<Registro> getRegistros() {
		return registros;
	}

	public void setRegistros(List<Registro> registros) {
		this.registros = registros;
	}


	public Registro getRegistro() {
		return registro;
	}


	public void setRegistro(Registro registro) {
		this.registro = registro;
	}
	
	
	
}
