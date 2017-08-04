package com.flavio.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.LazyDataModel;

import com.flavio.anotation.Email;
import com.flavio.model.Cliente;
import com.flavio.service.ClienteService;
import com.flavio.service.ContextMensage;
import com.flavio.service.Mensagem;
import com.flavio.util.Paginacao;

@Named
@RequestScoped
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ContextMensage contextMensage;
	@Inject
	@Email
	private Mensagem mensagem;
	@Inject
	private ClienteService clienteService;
	
	public static Log log = LogFactory.getLog(ClienteBean.class);
	
	private String nomePesquisa;
	private Paginacao paginacao = new Paginacao();
	
	private LazyDataModel<Cliente> model;
	private List<Cliente> clientes;
	private Cliente cliente;

	
	public void limpar() {
		cliente = new Cliente();
		log.info("passou pela limpeza...");
	}
	
	@PostConstruct
	public void init(){//consultaCategorias
		cliente = new Cliente();
		clientes = clienteService.listRepository();
		this.model = clienteService.consultaPaginada(paginacao);
	}
	
	public void buscar() {
		paginacao.setDescricao(this.nomePesquisa);
		model = clienteService.consultaPaginada(paginacao);
	}

	
	public void salvar() {
		try {
			if (clienteService.salvar(cliente)) {
				this.contextMensage.addmsg("", FacesMessage.SEVERITY_INFO, "Dados salvos com sucesso!",
						"Dados salvos com sucesso!");
				model = clienteService.consultaPaginada(paginacao);
				mensagem.enviar();
				limpar();
			}
		} catch (Exception e) {
			this.contextMensage.addmsg("", FacesMessage.SEVERITY_WARN, "Erro ao salvar!", "Erro ao salvar!");
			log.error("Erro ao Salvar Cliente: " + e);
			e.printStackTrace();
		}

	}

	public String list() {
		clientes = clienteService.listRepository();
		return "/cliente/listagem?faces-redirect=true";
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

	public LazyDataModel<Cliente> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<Cliente> model) {
		this.model = model;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
