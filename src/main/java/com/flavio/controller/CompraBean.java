/**
 * projeto-web : 8 de ago de 2017 
 */
package com.flavio.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.LazyDataModel;

import com.flavio.anotation.Email;
import com.flavio.model.Compra;
import com.flavio.model.Pedido;
import com.flavio.service.ContextMensage;
import com.flavio.service.Mensagem;
import com.flavio.service.PedidoService;
import com.flavio.util.Paginacao;

/**
 * @author flavio
 *
 */
@Named
public class CompraBean implements Serializable{

	private static final long serialVersionUID = 1L;
	

	@Inject
	private ContextMensage contextMensage;
	@Inject
	@Email
	private Mensagem mensagem;
	@Inject
	private PedidoService pedidoService;
	
	
	public static Log log = LogFactory.getLog(CompraBean.class);
	
	private List<Pedido> pedidos;

	private String nomePesquisa;
	private Paginacao paginacao = new Paginacao();

	private Pedido pedido = new Pedido();
	private Compra compra = new Compra();

	private LazyDataModel<Pedido> model;
	
	public String list(){
		return "/compras/listagem?faces-redirect=true";
	}
	
	
	public void salvar(){
		System.out.println("salvando...");
	}
	
	public void atualizaModel(AjaxBehaviorEvent event){
		System.out.println("Atualizando modelo autocomplete fonecedor... " + ((UIInput) event.getSource()).getValue());
		//this.pedido.setFornecedor(fornecedor);setAlugado((Integer) event.getNewValue());
		FacesContext.getCurrentInstance().renderResponse();
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public String getNomePesquisa() {
		return nomePesquisa;
	}

	public void setNomePesquisa(String nomePesquisa) {
		this.nomePesquisa = nomePesquisa;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public LazyDataModel<Pedido> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<Pedido> model) {
		this.model = model;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	
	
}
