package com.flavio.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.flavio.dao.DAOFactory;
import com.flavio.dao.VendedorDao;
import com.flavio.model.Vendedor;

@RequestScoped
public class ServiceVendedor {

	DAOFactory daoFactory = DAOFactory.getFactory();
	private VendedorDao vendedorDao;
	
	public ServiceVendedor() {
		// obtém a instancia do vendedorDao
		vendedorDao = daoFactory.getVendedorDAO();
	}

	public void salvar(Vendedor vendedor) {

		vendedorDao.save(vendedor);

	}

	public List<Vendedor> todos() {
		return vendedorDao.listAll();
	}

}
