/**Autor: Flávio Henrique
 * 9 de abr de 2017ProjetoBootStrap2
 */
package com.flavio.dao.implementDaos;

import java.util.List;

import javax.persistence.EntityManager;

import com.flavio.dao.interfaceDao.ProdutoDao;
import com.flavio.model.Produto;
import com.flavio.util.jpa.EntityManagerProducer;

/**
 * @author root
 *
 */
public class HibernateProdutoDao extends HibernateDAO<Produto, Long> implements ProdutoDao{

	/**
	 * @param persistentClass
	 */
	public HibernateProdutoDao() {
		super(Produto.class);
	}
	
	public List<Produto> listAllQ(){
		return ((EntityManager) EntityManagerProducer.getRequestAtribute("entityManager"))
				.createQuery("Produto.findAll",Produto.class)
				.getResultList();
	}

	public void delete(Produto entity) {
		
	}
}
