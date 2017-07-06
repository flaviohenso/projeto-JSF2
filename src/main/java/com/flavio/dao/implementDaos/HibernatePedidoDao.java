/**Autor: Flávio Henrique
 * 9 de abr de 2017ProjetoBootStrap2
 */
package com.flavio.dao.implementDaos;

import java.util.List;

import javax.persistence.EntityManager;

import com.flavio.conectionDB.JpaUtil;
import com.flavio.dao.interfaceDao.PedidoDao;
import com.flavio.model.Pedido;

/**
 * @author root
 *
 */
public class HibernatePedidoDao extends HibernateDAO<Pedido, Long> implements PedidoDao{

	/**
	 * @param persistentClass
	 */
	public HibernatePedidoDao() {
		super(Pedido.class);
	}
	
	public List<Pedido> listAllQ(){
		return ((EntityManager) JpaUtil.getRequestAtribute("entityManager"))
				.createQuery("Pedido.findAll",Pedido.class)
				.getResultList();
	}

	public void delete(Pedido entity) {
		
	}
}
