/**Autor: Flávio Henrique
 * 9 de abr de 2017ProjetoBootStrap2
 */
package com.flavio.dao.implementDaos;

import java.util.List;

import javax.persistence.EntityManager;

import com.flavio.dao.interfaceDao.VendedorDao;
import com.flavio.model.Vendedor;
import com.flavio.util.jpa.EntityManagerProducer;

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
	
	public List<Vendedor> listAll(){
		return ((EntityManager) EntityManagerProducer.getRequestAtribute("entityManager"))
				.createNamedQuery("Vendedor.findAll")
				.getResultList();
	}
	
	@Override
	public void delete(Vendedor entity) {
		
	}

}
