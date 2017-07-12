package com.flavio.util.jpa;

/**
 * NÂO ESTA SENDO UTILIZADA NO PROJETO
 */

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
//Criando a fábrica de Conexões
public class ConnectionDB {
	private static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("VendasPU");
	@SuppressWarnings("unused")
	private static EntityManager em;
	
	public static EntityManager getConnection(){
		return emf.createEntityManager();
	}
}
