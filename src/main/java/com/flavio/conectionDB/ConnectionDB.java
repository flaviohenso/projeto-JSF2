package com.flavio.conectionDB;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
//Criando a fábrica de Conexões
public class ConnectionDB {
	private static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("vendas");
	private static EntityManager em;
	
	public static EntityManager getConnection(){
		return emf.createEntityManager();
	}
}
