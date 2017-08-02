package com.flavio.repository;

import java.io.Serializable;

public interface GenericRepository<T,Type extends Serializable> {

	/**
	 * @param coluna representa a coluna do banco pela qual deseja realizar a busca é do tipo String
	 * @return retorna uma instancia de objeto
	 */
	public T objetoUnico(Long value, String coluna);
	
}
