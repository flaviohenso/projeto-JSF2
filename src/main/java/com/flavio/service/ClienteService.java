package com.flavio.service;

import java.util.List;

import org.primefaces.model.LazyDataModel;

import com.flavio.model.Cliente;
import com.flavio.util.Paginacao;

public class ClienteService implements GenericService<Cliente>{

	@Override
	public List<Cliente> listRepository() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean salvar(Cliente t) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remover(Cliente t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LazyDataModel<Cliente> consultaPaginada(Paginacao paginacao) {
		// TODO Auto-generated method stub
		return null;
	}

}
