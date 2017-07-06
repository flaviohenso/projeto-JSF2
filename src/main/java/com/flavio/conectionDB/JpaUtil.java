package com.flavio.conectionDB;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;

public class JpaUtil {

	private static final String PERSISTENCE_UNIT = "VendasPU";

	static EntityManagerFactory emf;

	private static ThreadLocal<EntityManager> threadLocalEntiryManager = new ThreadLocal<EntityManager>();

	public static EntityManager getConnection() {

		EntityManager entityManager = threadLocalEntiryManager.get();

		if (emf == null) {

			emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);

		}

		if (entityManager == null || !entityManager.isOpen()) {

			entityManager = emf.createEntityManager();

			JpaUtil.threadLocalEntiryManager.set(entityManager);

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
	//
	// public static void closeConection() {
	//
	// getConnection().close();
	//
	// }

	public static Object getRequestAtribute(String name) {
		FacesContext faceContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = faceContext.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		return request.getAttribute(name);
	}

	public static void closeEntityManager() {

		EntityManager em = threadLocalEntiryManager.get();

		if (em != null) {

			EntityTransaction entityTransaction = em.getTransaction();

			if (entityTransaction.isActive()) {

				entityTransaction.commit();

			}

			em.close();

			threadLocalEntiryManager.set(null);
		}

	}

	public static void closeEntityManagerFactory() {

		closeEntityManager();

		emf.close();

	}

}
