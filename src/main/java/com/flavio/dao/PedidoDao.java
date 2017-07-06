/**Autor: Flávio Henrique
 * 9 de abr de 2017ProjetoBootStrap2
 */
package com.flavio.dao;

import java.util.List;

import com.flavio.model.Pedido;

/**
 * @author root
 *
 */
public interface PedidoDao extends GenericDAO<Pedido, Long> {
	
	public List<Pedido> listAllQ();

}
