/**
 * projeto-web : 9 de ago de 2017 
 */
package com.flavio.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.LazyDataModel;

import com.flavio.anotation.Email;
import com.flavio.model.Fornecedor;
import com.flavio.service.ContextMensage;
import com.flavio.service.FornecedorService;
import com.flavio.service.Mensagem;
import com.flavio.util.Paginacao;

/**
 * @author flavio
 *
 */
@Named
@ViewScoped
public class FornecedorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ContextMensage contextMensage;
	@Inject
	@Email
	private Mensagem mensagem;
	@Inject
	private FornecedorService fornecedorService;

	public static Log log = LogFactory.getLog(FornecedorBean.class);

	private Fornecedor fornecedor;
	private List<Fornecedor> fornecedores;

	private String nomePesquisa;
	private Paginacao paginacao = new Paginacao();

	private LazyDataModel<Fornecedor> model;

	@PostConstruct
	public void init(){//consultaCategorias
		fornecedor = new Fornecedor();
		fornecedores = fornecedorService.listRepository();
		this.listaPagianda();
	}
	
	public void limpar() {
		fornecedor = new Fornecedor();
		log.info("passou pela limpeza...");
	}
	
	private void listaPagianda(){
		this.model = fornecedorService.consultaPaginada(paginacao);
	}
	
	public void buscar() {
		paginacao.setDescricao(this.nomePesquisa);
		this.listaPagianda();
	}
	
	public String list() {
		return "/fornecedor/listagem?faces-redirect=true";
	}
	
	public void salvar() {
		try {
			if (fornecedorService.salvar(fornecedor)) {
				this.contextMensage.addmsg("", FacesMessage.SEVERITY_INFO, "Dados salvos com sucesso!",
						"Dados salvos com sucesso!");
				this.listaPagianda();
				mensagem.enviar();
				limpar();
			}
		} catch (Exception e) {
			this.contextMensage.addmsg("", FacesMessage.SEVERITY_WARN, "Erro ao salvar!", "Erro ao salvar!");
			log.error("Erro ao Salvar Fornecedor: " + e);
			e.printStackTrace();
		}

	}
	
	public void remover(){
		try {
			if (fornecedorService.remover(fornecedor)) {
				this.listaPagianda();
				this.contextMensage.addmsg("", FacesMessage.SEVERITY_INFO, "Fornecedor removido com sucesso!",
						"Fornecedor removido com sucesso!");
			} else {
				this.contextMensage.addmsg("", FacesMessage.SEVERITY_WARN, "Erro ao remover!", "Erro ao remover!");
			}
		} catch (Exception e) {
			this.contextMensage.addmsg("", FacesMessage.SEVERITY_WARN, e.getMessage(), e.getMessage());
			e.printStackTrace();
		}
		this.limpar();
	}
	
	
	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getNomePesquisa() {
		return nomePesquisa;
	}

	public void setNomePesquisa(String nomePesquisa) {
		this.nomePesquisa = nomePesquisa;
	}

	public Paginacao getPaginacao() {
		return paginacao;
	}

	public void setPaginacao(Paginacao paginacao) {
		this.paginacao = paginacao;
	}

	public LazyDataModel<Fornecedor> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<Fornecedor> model) {
		this.model = model;
	}
	
}
