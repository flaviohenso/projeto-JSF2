package com.flavio.service;

import java.util.List;

public interface GenericService<T> {
	public List<T> listRepository();
	public boolean salvar(T t) throws Exception;
}
