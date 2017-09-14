/**
 * projeto-web : 9 de ago de 2017 
 */
package com.flavio.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.flavio.model.Fornecedor;
import com.flavio.repository.FornecedorRepository;
import com.flavio.util.Paginacao;

/**
 * @author flavio
 *
 */
public class FornecedorService implements GenericService<Fornecedor>, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private FornecedorRepository fornecedorRepository;
	
	@Override
	public List<Fornecedor> listRepository() {
		return null;
	}

	@Override
	public boolean salvar(Fornecedor fornecedor) throws Exception {

		if (fornecedorRepository.save(fornecedor)) {
			return true;
		} else {
			System.out.println("Erro ao salvar!");
			return false;
		}
	}

	@Override
	public boolean remover(Fornecedor t) throws Exception {
		return false;
	}

	@Override
	public LazyDataModel<Fornecedor> consultaPaginada(Paginacao paginacao) {
		return new LazyDataModel<Fornecedor>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Fornecedor> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				paginacao.setPrimeiroRegistro(first);
				paginacao.setQuantidadeRegistros(pageSize);
				paginacao.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				paginacao.setPropriedadeOrdenacao(sortField);

				setRowCount(fornecedorRepository.quantidadeFiltrados(paginacao));

				return fornecedorRepository.filtrados(paginacao);
			}

		};
	}

	/**
	 * @param term
	 * @return
	 */
	public ArrayList<String> autoComplete(String term) {
		
		ArrayList<String> fornecedoresString = new ArrayList<String>();
		List<Fornecedor> fornecedores;
		
		fornecedores = fornecedorRepository.buscarPorNome(term);
		
		for (Fornecedor fornecedor : fornecedores) {
			fornecedoresString.add(fornecedor.getNome()+ ":" +fornecedor.getCnpj());
		}
		
		return fornecedoresString;
	}
	
	public List<Fornecedor> autoComplete2(String term) {
		return fornecedorRepository.buscarPorNome(term);
	}

}
