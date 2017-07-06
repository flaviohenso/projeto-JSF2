/**Autor: Flávio Henrique
 * 9 de abr de 2017ProjetoBootStrap2
 */
package com.flavio.dao.implementDaos;

import java.util.List;

import javax.persistence.EntityManager;

import com.flavio.conectionDB.JpaUtil;
import com.flavio.dao.interfaceDao.VendedorDao;
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
	
	public List<Vendedor> listAllQ(){
		return ((EntityManager) JpaUtil.getRequestAtribute("entityManager"))
				.createQuery("Vendedor.findAll",Vendedor.class)
				.getResultList();
	}
	
	@Override
	public void delete(Vendedor entity) {
		
	}

}
