package com.flavio.dao.interfaceDao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

//interface que possui o metodos comuns para todos os DAOs

public interface GenericDAO<T,Type extends Serializable> {
//	public void beginTransaction();
//
//	public void commitTransaction();

	public void save(T entity, EntityManager entityManager) throws Exception;

	public void delete (T entity, EntityManager entityManager);
	
	public List<T> listAllD(EntityManager entityManager);

	/**
	 * @param coluna representa a coluna do banco pela qual deseja ordenar a lista e é do tipo String
	 * @return retorna uma lista ordenada pela o parametro coluna
	 */
	public List<T> listAllascD(String coluna, EntityManager entityManager);

//	public void closeTransaction();
	
	/**
	 * @param coluna representa a coluna do banco pela qual deseja realizar a busca é do tipo String
	 * @return retorna uma instancia de objeto
	 */
	public T objetoUnicoD(Integer value, String coluna, EntityManager entityManager);
}
