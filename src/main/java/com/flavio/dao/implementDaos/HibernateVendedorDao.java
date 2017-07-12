/**Autor: Flávio Henrique
 * 9 de abr de 2017ProjetoBootStrap2
 */
package com.flavio.dao.implementDaos;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;

import com.flavio.dao.interfaceDao.VendedorDao;
import com.flavio.model.Vendedor;

/**
 * @author root
 *
 */
@RequestScoped
public class HibernateVendedorDao extends HibernateDAO<Vendedor, Long> implements VendedorDao{
	
	/**
	 * @param persistentClass
	 */
	
	public HibernateVendedorDao() {
		super(Vendedor.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Vendedor> listAll(EntityManager em){
		if(em != null){
			System.out.println("Entity Válido");
		}else{
			System.out.println("dentro de HibernateDao é null");
		}
		
		return em.createNamedQuery("Vendedor.findAll")
				.getResultList();
	}
	
	@Override
	public void delete(Vendedor entity, EntityManager entityManager) {
		
	}

}
