package com.flavio.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.flavio.model.Authoritie;
import com.flavio.util.jpa.EntityManagerProducer;

public class AuthoritieRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static Log log = LogFactory.getLog(AuthoritieRepository.class);
	
	@Inject
	private EntityManager entityManager;
	
	public boolean save(Authoritie authoritie) throws Exception {
		if (entityManager != null) {
			try {
				EntityManagerProducer.beginTransaction(entityManager);
				this.entityManager.persist(authoritie);
				return true;
			} catch (Exception e) {
				log.error("Ocorreu um erro ao salvar authority: " + e.getMessage());
				return false;
			}
		} else {
			return false;
		}
	}
	
	public List<Authoritie> listAll(){
		return entityManager.createNamedQuery("Authorities.findAll",Authoritie.class).getResultList();
	}

}
