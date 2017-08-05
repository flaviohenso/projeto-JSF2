package com.flavio.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.flavio.model.Vendedor;
import com.flavio.repository.VendedorRepository;
import com.flavio.util.Paginacao;
import com.flavio.util.security.MD5;

@RequestScoped
public class ServiceVendedor implements GenericService<Vendedor>, Serializable{

//	private DAOFactory daoFactory = DAOFactory.getFactory();
//	private VendedorDao vendedorDao;
	
	private static final long serialVersionUID = 1L;
	
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
	
	public boolean salvar(Vendedor vendedor) throws Exception{
		vendedor.setSenha(MD5.convertPasswordMd5(vendedor.getSenha()));
		if(vendedorRepository.save(vendedor)){
			System.out.println("salvou com suscesso");
			return true;
		}else {
			System.out.println("Erro ao salvar Vendedor!");
			return false;
		}

	}

	@Override
	public boolean remover(Vendedor t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LazyDataModel<Vendedor> consultaPaginada(Paginacao paginacao) {
		return new LazyDataModel<Vendedor>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Vendedor> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				paginacao.setPrimeiroRegistro(first);
				paginacao.setQuantidadeRegistros(pageSize);
				paginacao.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				paginacao.setPropriedadeOrdenacao(sortField);

				setRowCount(vendedorRepository.quantidadeFiltrados(paginacao));

				return vendedorRepository.filtrados(paginacao);
			}

		};
	}
}
