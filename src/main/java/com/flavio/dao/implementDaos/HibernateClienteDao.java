/**Autor: Flávio Henrique
 * 9 de abr de 2017ProjetoBootStrap2
 */
package com.flavio.dao.implementDaos;

import java.util.List;

import javax.persistence.EntityManager;

import com.flavio.dao.interfaceDao.ClienteDao;
import com.flavio.model.Cliente;
import com.flavio.util.jpa.EntityManagerProducer;

/**
 * @author root
 *
 */
public class HibernateClienteDao extends HibernateDAO<Cliente, Long> implements ClienteDao{

	/**
	 * @param persistentClass
	 */
	public HibernateClienteDao() {
		super(Cliente.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> listAllQ(){
		return ((EntityManager) EntityManagerProducer.getRequestAtribute("entityManager"))
				.createQuery("Cliente.findAll")
				.getResultList();
	}
	
	@Override
	public void delete(Cliente entity, EntityManager entityManager) {
		
	}

}
