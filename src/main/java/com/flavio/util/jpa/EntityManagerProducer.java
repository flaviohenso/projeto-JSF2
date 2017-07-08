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

@ApplicationScoped
public class EntityManagerProducer {

	private static final String PERSISTENCE_UNIT = "VendasPU";

	private EntityManagerFactory emf;

	private static ThreadLocal<EntityManager> threadLocalEntiryManager = new ThreadLocal<EntityManager>();

	public EntityManagerProducer() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
	}

	@Produces
	@RequestScoped
	public EntityManager getConnection() {

		EntityManager entityManager = threadLocalEntiryManager.get();

		if (entityManager == null || !entityManager.isOpen()) {

			entityManager = emf.createEntityManager();

			EntityManagerProducer.threadLocalEntiryManager.set(entityManager);

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

		if (threadLocalEntiryManager.equals(entityManager)) {
		
			em = threadLocalEntiryManager.get();

			if (em != null) {

				EntityTransaction entityTransaction = em.getTransaction();

				if (entityTransaction.isActive()) {

					entityTransaction.commit();

				}

				em.close();

				threadLocalEntiryManager.set(null);
			}
		}

	}

//	public void closeEntityManagerFactory() {
//
//		closeEntityManager();
//
//		emf.close();
//
//	}

}
