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
import com.flavio.model.Produto;
import com.flavio.service.ContextMensage;
import com.flavio.service.Mensagem;
import com.flavio.service.ProdutoService;
import com.flavio.util.Paginacao;

@Named
@ViewScoped
public class ProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ContextMensage contextMensage;
	@Inject
	@Email
	private Mensagem mensagem;
	@Inject
	private ProdutoService produtoService;

	public static Log log = LogFactory.getLog(ProdutoBean.class);

	private List<Produto> produtos;

	private String nomePesquisa;
	private Paginacao paginacao = new Paginacao();

	private Produto produto = new Produto();

	private LazyDataModel<Produto> model;

	public void limpar() {
		produto = new Produto();
		log.info("passou pela limpeza...");
	}

	@PostConstruct
	public void consultaProdutos() {
		this.model = produtoService.consultaPaginada(paginacao);
	}

	public void buscar() {
		paginacao.setDescricao(this.nomePesquisa);
		model = produtoService.consultaPaginada(paginacao);
	}

	public void edit() {
		System.out.println(produto.getNome() + " produto para editar");
		// this.produto = produtoService.produtoByID(produto.getId());
	}

	public void salvar() {
		try {
			if (produtoService.salvar(produto)) {
				this.contextMensage.addmsg("", FacesMessage.SEVERITY_INFO, "Dados salvos com sucesso!",
						"Dados salvos com sucesso!");
				model = produtoService.consultaPaginada(paginacao);
				mensagem.enviar();
				limpar();
			}
		} catch (Exception e) {
			this.contextMensage.addmsg("", FacesMessage.SEVERITY_WARN, "Erro ao salvar!", "Erro ao salvar!");
			log.error("Erro ao Salvar Produto: " + e);
			e.printStackTrace();
		}

	}

	public String list() {
		List<Produto> listRepository = produtoService.listRepository();

		if (listRepository != null) {
			produtos = listRepository;
		}

		return "/produto/listagem?faces-redirect=true";
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public String getNomePesquisa() {
		return nomePesquisa;
	}

	public void setNomePesquisa(String nomePesquisa) {
		this.nomePesquisa = nomePesquisa;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public LazyDataModel<Produto> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<Produto> model) {
		this.model = model;
	}

	public Paginacao getPaginacao() {
		return paginacao;
	}

	public void setPaginacao(Paginacao paginacao) {
		this.paginacao = paginacao;
	}

}
