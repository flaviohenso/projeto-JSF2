/**Autor: Flávio Henrique
 * 9 de abr de 2017ProjetoBootStrap2
 */
package com.flavio.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

//@NamedQueries({ 
//	@NamedQuery(name = "Vendedor.findAll", query = "SELECT v FROM Vendedor v")
//})
@Entity
/*
 * @DiscriminatorValue contem o valor que irá identificar cada classe na tabela do
 * banco de dados
 */
@DiscriminatorValue("vendedor")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Cacheable(true)
public class Vendedor extends Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<Venda> vendas = new ArrayList<Venda>();

	/*
	 * @OneToMany(mappedBy = "vendedor"): defini que a chave estrangeira vai esta na Entity Pedido
	 */
	@OneToMany(mappedBy = "vendedor")
	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

}
