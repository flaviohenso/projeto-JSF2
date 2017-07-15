package com.flavio.service;

import java.util.List;

public interface GenericService<T> {
	public List<T> listRepository();
	public void salvar(T t) throws Exception;
}
