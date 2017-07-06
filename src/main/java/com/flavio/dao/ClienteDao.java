/**Autor: Flávio Henrique
 * 9 de abr de 2017ProjetoBootStrap2
 */
package com.flavio.dao;

import java.util.List;

import com.flavio.model.Cliente;

/**
 * @author root
 *
 */
public interface ClienteDao extends GenericDAO<Cliente, Long> {
	
	public List<Cliente> listAllQ();

}
