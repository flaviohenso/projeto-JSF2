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
import com.flavio.exception.CategoriaException;
import com.flavio.model.Categoria;
import com.flavio.service.CategoriasService;
import com.flavio.service.ContextMensage;
import com.flavio.service.Mensagem;
import com.flavio.util.Paginacao;

@Named
@ViewScoped
public class CategoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ContextMensage contextMensage;
	@Inject
	@Email
	private Mensagem mensagem;
	@Inject
	private CategoriasService categoriasService;

	public static Log log = LogFactory.getLog(CategoriaBean.class);

	private List<Categoria> categorias;

	private String nomePesquisa;
	private Paginacao paginacao = new Paginacao();

	private Categoria categoria;

	private LazyDataModel<Categoria> model;
	
	
	public void limpar() {
		categoria = new Categoria();
		log.info("passou pela limpeza...");
	}
	
	@PostConstruct
	public void init(){//consultaCategorias
		categoria = new Categoria();
		categorias = categoriasService.listRepository();
		this.listaPagianda();
	}
	
	public void buscar() {
		paginacao.setDescricao(this.nomePesquisa);
		this.listaPagianda();
	}
	
	public void remover(){
		try {
			if(categoriasService.remover(categoria)){
				this.listaPagianda();
				this.contextMensage.addmsg("", FacesMessage.SEVERITY_INFO, "Categoria removido com sucesso!",
						"Categoria removido com sucesso!");
			}
		} catch (CategoriaException e) {
			this.contextMensage.addmsg("", FacesMessage.SEVERITY_WARN, e.getMessage(), e.getMessage());
			e.printStackTrace();
		}
		this.limpar();
	}
	
	private void listaPagianda(){
		this.model = categoriasService.consultaPaginada(paginacao);
	}
	
	public void salvar() {
		try {
			if (categoriasService.salvar(categoria)) {
				this.contextMensage.addmsg("", FacesMessage.SEVERITY_INFO, "Dados salvos com sucesso!",
						"Dados salvos com sucesso!");
				this.listaPagianda();
				mensagem.enviar();
				limpar();
			}
		} catch (Exception e) {
			this.contextMensage.addmsg("", FacesMessage.SEVERITY_WARN, "Erro ao salvar!", "Erro ao salvar!");
			log.error("Erro ao Salvar Categoria: " + e);
			e.printStackTrace();
		}

	}

	public String list() {
		categorias = categoriasService.listRepository();
		return "/categoria/listagem?faces-redirect=true";
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
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

	public LazyDataModel<Categoria> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<Categoria> model) {
		this.model = model;
	}

}
