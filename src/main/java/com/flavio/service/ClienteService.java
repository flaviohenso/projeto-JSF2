package com.flavio.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.flavio.model.Cliente;
import com.flavio.repository.ClienteRepository;
import com.flavio.util.Paginacao;
import com.flavio.util.security.MD5;

public class ClienteService implements GenericService<Cliente>, Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ClienteRepository clienteRepository;
	
	@Override
	public List<Cliente> listRepository() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean salvar(Cliente cliente) throws Exception {
		cliente.setSenha(MD5.convertPasswordMd5(cliente.getSenha()));
		if(clienteRepository.save(cliente)){
			return true;
		}
		
		return false;
	}

	@Override
	public boolean remover(Cliente cliente) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LazyDataModel<Cliente> consultaPaginada(Paginacao paginacao) {
		return new LazyDataModel<Cliente>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Cliente> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				paginacao.setPrimeiroRegistro(first);
				paginacao.setQuantidadeRegistros(pageSize);
				paginacao.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				paginacao.setPropriedadeOrdenacao(sortField);

				setRowCount(clienteRepository.quantidadeFiltrados(paginacao));

				return clienteRepository.filtrados(paginacao);
			}

		};
	}

}
