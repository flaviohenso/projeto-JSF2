package com.flavio.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.flavio.model.Vendedor;
import com.flavio.util.jpa.EntityManagerProducer;

public class VendedorRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	public static Log log = LogFactory.getLog(VendedorRepository.class);

	@Inject
	private EntityManager entityManager;

	public List<Vendedor> listAll() {
		return entityManager.createNamedQuery("Vendedor.findAll",Vendedor.class).getResultList();
	}

	public boolean save(Vendedor vendedor) throws Exception {
		if (entityManager != null) {
			try {
				EntityManagerProducer.beginTransaction(entityManager);
				entityManager.persist(vendedor);
				return true;
			} catch (Exception e) {
				log.error("Ocorreu um erro ao salvar vendedor: " + e.getMessage());
				return false;
			}
		} else {
			return false;
		}
	}

}
