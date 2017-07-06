/**Autor: Flávio Henrique
 * 9 de abr de 2017ProjetoBootStrap2
 */
package com.flavio.dao;

import java.util.List;

import com.flavio.model.Usuario;
import com.flavio.model.Vendedor;

/**
 * @author root
 *
 */
public interface VendedorDao extends GenericDAO<Vendedor, Long> {
	
	public List<Vendedor> listAllQ();

}
