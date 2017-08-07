package com.flavio.service;

import java.util.List;

import org.primefaces.model.LazyDataModel;

import com.flavio.util.Paginacao;

public interface GenericService<T> {
	public List<T> listRepository();
	public boolean salvar(T t) throws Exception;
	public boolean remover(T t) throws Exception;
	public LazyDataModel<T> consultaPaginada(Paginacao paginacao);
}
