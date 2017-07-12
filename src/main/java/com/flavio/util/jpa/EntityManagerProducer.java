package com.flavio.util.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@ApplicationScoped
public class EntityManagerProducer {

	private static final String PERSISTENCE_UNIT = "VendasPU";
	public static Log log = LogFactory.getLog(EntityManagerProducer.class);
	
	//@PersistenceUnit(unitName = "VendasPU")
	private EntityManagerFactory emf;

	private static ThreadLocal<EntityManager> threadLocalEntityManager = new ThreadLocal<EntityManager>();

	public EntityManagerProducer() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
	}

	@Produces
	@RequestScoped
	public EntityManager getConnection() {

		EntityManager entityManager = threadLocalEntityManager.get();

		if (entityManager == null || !entityManager.isOpen()) {

			entityManager = emf.createEntityManager();

			EntityManagerProducer.threadLocalEntityManager.set(entityManager);

		}

		return entityManager;

	}
	
	public static void beginTransaction(EntityManager em) {
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
		}
	}

	public static void commitTransaction(EntityManager em) {
		if (em != null && em.getTransaction().isActive()) {
			em.getTransaction().commit();
		}
	}

	public static void rollBackTransaction(EntityManager em) {

		em.getTransaction().rollback();

	}

	public static Object getRequestAtribute(String name) {
		FacesContext faceContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = faceContext.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		return request.getAttribute(name);
	}

	public static void closeEntityManager(@Disposes EntityManager entityManager) {
		EntityManager em;

		if (threadLocalEntityManager.get() == entityManager) {
		
			em = threadLocalEntityManager.get();

			if (em != null) {

				EntityTransaction entityTransaction = em.getTransaction();

				if (entityTransaction.isActive()) {

					entityTransaction.commit();
					System.out.println("close comit");
				}

				em.close();
				log.info("EntityManager foi fechada");
				threadLocalEntityManager.set(null);
			}
		}

	}

}
