package com.flavio.dao.interfaceDao;

import java.io.Serializable;
import java.util.List;

//interface que possui o metodos comuns para todos os DAOs

public interface GenericDAO<T,Type extends Serializable> {
//	public void beginTransaction();
//
//	public void commitTransaction();

	public void save(T entity) throws Exception;

	public void delete (T entity);
	
	public List<T> listAll();

	/**
	 * @param coluna representa a coluna do banco pela qual deseja ordenar a lista e é do tipo String
	 * @return retorna uma lista ordenada pela o parametro coluna
	 */
	public List<T> listAllasc(String coluna);

//	public void closeTransaction();
	
	/**
	 * @param coluna representa a coluna do banco pela qual deseja realizar a busca é do tipo String
	 * @return retorna uma instancia de objeto
	 */
	public T objetoUnico(Integer value, String coluna);
}
