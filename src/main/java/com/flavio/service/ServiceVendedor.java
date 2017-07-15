package com.flavio.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.flavio.model.Vendedor;
import com.flavio.repository.VendedorRepository;
import com.flavio.util.security.MD5;

@RequestScoped
public class ServiceVendedor implements GenericService<Vendedor>{

//	private DAOFactory daoFactory = DAOFactory.getFactory();
//	private VendedorDao vendedorDao;
	
	@Inject
	private VendedorRepository vendedorRepository;
	
	public ServiceVendedor() {
		/*
		 * obtém a instancia do vendedorDao
		 */
//		vendedorDao = daoFactory.getVendedorDAO();
	}

	public List<Vendedor> listRepository(){
		return vendedorRepository.listAll();
	}
	
	public void salvar(Vendedor vendedor) throws Exception{
		vendedor.setSenha(MD5.convertPasswordMd5(vendedor.getSenha()));
		if(vendedorRepository.save(vendedor)){
			System.out.println("salvou com suscesso");
		}else {
			System.out.println("Erro ao salvar Vendedor!");
		}
//		vendedorDao.save(vendedor, em);

	}

//	public List<Vendedor> todos() {
//		if(em != null){
//			System.out.println("Entity Válido");
//		}
//		return vendedorDao.listAll(em);
//	}

}
