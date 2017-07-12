/**Autor: Flávio Henrique
 * 9 de abr de 2017ProjetoBootStrap2
 */
package com.flavio.dao.interfaceDao;

import java.util.List;

import javax.persistence.EntityManager;

import com.flavio.model.Vendedor;

/**
 * @author root
 *
 */
public interface VendedorDao extends GenericDAO<Vendedor, Long> {
	
	public List<Vendedor> listAll(EntityManager em);

}
