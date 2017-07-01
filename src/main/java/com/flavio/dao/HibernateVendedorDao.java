/**Autor: Flávio Henrique
 * 9 de abr de 2017ProjetoBootStrap2
 */
package com.flavio.dao;

import com.flavio.model.Vendedor;

/**
 * @author root
 *
 */
public class HibernateVendedorDao extends HibernateDAO<Vendedor, Long> implements VendedorDao{

	/**
	 * @param persistentClass
	 */
	public HibernateVendedorDao() {
		super(Vendedor.class);
	}

	@Override
	public void delete(Vendedor entity) {
		
	}

}
