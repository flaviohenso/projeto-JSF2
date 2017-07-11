/**Autor: Flávio Henrique
 * 9 de abr de 2017ProjetoBootStrap2
 */
package com.flavio.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import com.flavio.anotation.Sms;
import com.flavio.model.Vendedor;
import com.flavio.service.ContextMensage;
import com.flavio.service.Mensagem;
import com.flavio.service.ServiceVendedor;

/**
 * @author root
 *
 * Quando usa o escopo SessionScoped ou outro tipo de escopo que mante a vida do Bean por muito tempo é necessário
 * implementar Serializable, caso contrário um exception do tipo UnserializableDependencyException é gerada.
 */
@Named
@SessionScoped
public class VendedorBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Vendedor> vendedores;
	private Vendedor vendedor;

	/**
	 * verificar se é necessário o construtor padrão nos beans CDI
	 * 
	 * **/
	public VendedorBean() {
	}
	
	/**
	 * Anotação do CDI que faz a injeção da dependencia
	 */
	@Inject
	private ServiceVendedor serviceVendedor;
	@Inject
	private ContextMensage contextMensage;
	
	
	/**
	 * Injetando uma interface e especificando por anotação qual classe que implementa essa interface
	 *  deve ser injetada
	 * **/
	@Inject @Sms
	private Mensagem mensagem;
	
	/**
	 * Metodo chamado após a injeção das dependencias e do construtor da classe
	 * 
	 * */
	@PostConstruct
	public void init(){
		vendedor = new Vendedor();
		vendedores = new ArrayList<Vendedor>();
	}
	
	
	public void salvar(){
		
		try {
			serviceVendedor.salvar(vendedor);
			mensagem.enviar();
			this.contextMensage.addmsg("", FacesMessage.SEVERITY_INFO, "Dados salvos com sucesso!", "Dados salvos com sucesso!");
		} catch (Exception e) {
			System.out.println("Erro ao salvar vendedor!!! <<<<<<<<<");
			this.contextMensage.addmsg("", FacesMessage.SEVERITY_WARN, "Erro ao salvar!", "Erro ao salvar!");
		} finally {
			this.vendedor = new Vendedor();
		}
	}
	
	public void listVendedores(ActionEvent event){
		vendedores = serviceVendedor.listRepository();
	}
	
	public String listVendedores(){
		vendedores = serviceVendedor.listRepository();
		if(vendedores == null){
			this.contextMensage.addmsg("", FacesMessage.SEVERITY_ERROR, "Erro ao consultar vendedores!", "Vendedores esta NULL!");
		}
		return "listagem?faces-redirect=true";
	}


	public List<Vendedor> getVendedores() {
		return vendedores;
	}


	public void setVendedores(List<Vendedor> vendedores) {
		this.vendedores = vendedores;
	}


	public Vendedor getVendedor() {
		return vendedor;
	}


	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	
	
}
