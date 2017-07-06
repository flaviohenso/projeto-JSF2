/**Autor: Flávio Henrique
 * 9 de abr de 2017ProjetoBootStrap2
 */
package com.flavio.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@NamedQueries({ 
	@NamedQuery(name = "Vendedor.findAll", query = "SELECT u FROM Vendedor u")
})
@Entity
/*
 * @DiscriminatorValue contem o valor que irá identificar cada classe na tabela do
 * banco de dados
 */
@DiscriminatorValue("vendedor")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Vendedor extends Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<Pedido> pedidos = new ArrayList<Pedido>();;

	/*
	 * @OneToMany(mappedBy = "vendedor"): defini que a chave estrangeira vai esta na Entity Pedido
	 */
	@OneToMany(mappedBy = "vendedor")
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

}
