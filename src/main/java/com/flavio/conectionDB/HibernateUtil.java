package com.flavio.conectionDB;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;


public class HibernateUtil {

//	private static SessionFactory sessionFactory = new Configuration()
//			.configure().buildSessionFactory();
	
	static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("vendas");
	@SuppressWarnings("unused")
	private static EntityManager em;
	
	private static ThreadLocal<EntityManager> threadLocal = new ThreadLocal<EntityManager>();

	public static EntityManager getConnection(){

		EntityManager entityManager = threadLocal.get();

		if (entityManager == null) {

			entityManager = emf.createEntityManager();

			threadLocal.set(entityManager);

		}

		return entityManager;

	}

//	public static void beginTransaction() {
//		if(!getConnection().getTransaction().isActive()){
//			getConnection().getTransaction().begin();
//		}
//	}
//
//	public static void commitTransaction() {
//
//		getConnection().getTransaction().commit();
//
//	}
//
//	public static void rollBackTransaction() {
//
//		getConnection().getTransaction().rollback();
//
//	}
//
//	public static void closeConection() {
//
//		getConnection().close();
//
//	}
	
	public static Object getRequestAtribute(String name){
		FacesContext faceContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = faceContext.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		return request.getAttribute(name);
	}
	
	public static void closeEntityManagerFactory() {

		emf.close();

	}

	
}
