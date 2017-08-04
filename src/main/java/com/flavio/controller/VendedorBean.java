/**Autor: Flávio Henrique
 * 9 de abr de 2017ProjetoBootStrap2
 */
package com.flavio.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.LazyDataModel;

import com.flavio.anotation.Sms;
import com.flavio.model.Vendedor;
import com.flavio.service.ContextMensage;
import com.flavio.service.Mensagem;
import com.flavio.service.ServiceVendedor;
import com.flavio.util.Paginacao;

/**
 * @author root
 *
 *         Quando usa o escopo SessionScoped ou outro tipo de escopo que mante a
 *         vida do Bean por muito tempo é necessário implementar Serializable,
 *         caso contrário um exception do tipo UnserializableDependencyException
 *         é gerada.
 */
@Named
@SessionScoped
public class VendedorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Vendedor> vendedores;
	private Vendedor vendedor = new Vendedor();

	public static Log log = LogFactory.getLog(VendedorBean.class);

	private String nomePesquisa;
	private Paginacao paginacao = new Paginacao();

	private LazyDataModel<Vendedor> model;

	/**
	 * Anotação do CDI que faz a injeção da dependencia
	 */
	@Inject
	private ServiceVendedor vendedorService;
	@Inject
	private ContextMensage contextMensage;

	/**
	 * Injetando uma interface e especificando por anotação qual classe que
	 * implementa essa interface deve ser injetada
	 **/
	@Inject
	@Sms
	private Mensagem mensagem;

	/**
	 * Metodo chamado após a injeção das dependencias e do construtor da classe
	 * 
	 */
	@PostConstruct
	public void init() {
		this.model = vendedorService.consultaPaginada(paginacao);
	}

	public void limpar() {
		vendedor = new Vendedor();
		log.info("passou pela limpeza...");
	}

	public void buscar() {
		paginacao.setDescricao(this.nomePesquisa);
		model = vendedorService.consultaPaginada(paginacao);
	}
	
	public void edit(){}
	
	public void salvar() {

		try {
			if (vendedorService.salvar(vendedor)) {
				this.contextMensage.addmsg("", FacesMessage.SEVERITY_INFO, "Dados salvos com sucesso!",
						"Dados salvos com sucesso!");
				model = vendedorService.consultaPaginada(paginacao);
				mensagem.enviar();
				limpar();
			}
		} catch (Exception e) {
			this.contextMensage.addmsg("", FacesMessage.SEVERITY_WARN, "Erro ao salvar!", "Erro ao salvar!");
			log.error("Erro ao Salvar Produto: " + e);
		}
	}

	public String list() {
		//vendedores = vendedorService.listRepository();
		this.model = vendedorService.consultaPaginada(paginacao);
		return "/vendedor/listagem?faces-redirect=true";
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

	public LazyDataModel<Vendedor> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<Vendedor> model) {
		this.model = model;
	}

}
