/**
 * projeto-web : 9 de ago de 2017 
 */
package com.flavio.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author flavio
 *
 */
@Entity(name = "venda")
public class Venda implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Vendedor vendedor;
	private Pedido pedido;
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "vendedor_id", nullable = false)
	public Vendedor getVendedor() {
		return vendedor;
	}
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	
	
}
