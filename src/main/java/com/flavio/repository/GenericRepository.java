package com.flavio.repository;

import java.io.Serializable;
import java.util.List;

import com.flavio.util.Paginacao;

public interface GenericRepository<T,Type extends Serializable> {

		
	public List<T> filtrados(Paginacao paginacao);
	
	public int quantidadeFiltrados(Paginacao paginacao);
	
	public boolean save(T entity) throws Exception;
	
	public List<T> listAll() ;
	
	public boolean remover(T entity);
	
	public T BuscarPorID(Long id);
	
}
