package com.flavio.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.flavio.anotation.Email;
import com.flavio.model.Authoritie;
import com.flavio.service.AuthoritieService;
import com.flavio.service.ContextMensage;
import com.flavio.service.Mensagem;

@Named
@SessionScoped
public class SistemaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ContextMensage contextMensage;
	@Inject
	@Email
	private Mensagem mensagem;
	@Inject
	private AuthoritieService authoritieService;

	private String nomePesquisa;

	public static Log log = LogFactory.getLog(SistemaBean.class);

	/*
	 * Esse atributo é usado no processo de Exclusão e Criação de um novo Authoritie
	 */
	private Authoritie authoritie = new Authoritie();
	private List<Authoritie> authorities = new ArrayList<Authoritie>();

	public void salvar() {
		try {
			//contextMensage.delMsg();
			//log.info("passou pela limpeza...");
			if (authoritieService.salvar(authoritie)) {
				this.contextMensage.addmsg("", FacesMessage.SEVERITY_INFO, "Dados salvos com sucesso!",
						"Dados salvos com sucesso!");
				authorities = authoritieService.listRepository();
				// mensagem.enviar();
			}
		} catch (Exception e) {
			this.contextMensage.addmsg("", FacesMessage.SEVERITY_WARN, "Erro ao salvar!", "Erro ao salvar!");
			log.error("Erro ao Salvar Authoritie: " + e);
		} finally {
			this.authoritie = new Authoritie();
			// contextMensage.delMsg();
		}
	}
	
	public void limparMsg() {
		contextMensage.delMsg();
	}
	
	public void limpar() {
		authoritie = new Authoritie();
		log.info("passou pela limpeza...");
	}

	public void buscar() {
		authorities = authoritieService.buscarPorNome(this.nomePesquisa);
	}

	public void remover() {
		if(authoritieService.remover(authoritie)){
			this.authorities = authoritieService.listRepository();
			this.contextMensage.addmsg("", FacesMessage.SEVERITY_INFO, "Authoritie removido com sucesso!",
					"Authoritie removido com sucesso!");
		}else{
			this.contextMensage.addmsg("", FacesMessage.SEVERITY_WARN, "Erro ao salvar!", "Erro ao salvar!");
		}
		this.limpar();
	}

	public String listAuthoritie() {
		authorities = authoritieService.listRepository();
		return "/sistema/seguranca/listagem?faces-redirect=true";
	}

	public Authoritie getAuthoritie() {
		return authoritie;
	}

	public void setAuthoritie(Authoritie authoritie) {
		this.authoritie = authoritie;
	}

	public List<Authoritie> getAuthorities() {
		return authorities;
	}

	public String getNomePesquisa() {
		return nomePesquisa;
	}

	public void setNomePesquisa(String nomePesquisa) {
		this.nomePesquisa = nomePesquisa;
	}

	public void setAuthorities(List<Authoritie> authorities) {
		this.authorities = authorities;
	}

}
