package com.flavio.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.flavio.model.Produto;
import com.flavio.repository.ProdutoRepository;
import com.flavio.util.Paginacao;

public class ProdutoService implements GenericService<Produto> {
	
	@Inject
	private ProdutoRepository produtoRepository;

	@Override
	public List<Produto> listRepository() {
		return produtoRepository.listAll();
	}

	@Override
	public boolean salvar(Produto produto) throws Exception {
		
		if(produtoRepository.save(produto)){
			System.out.println("salvou com suscesso");
			return true;
		}else{
			System.out.println("Erro ao salvar!");
			return false;
		}
		
	}

	@Override
	public boolean remover(Produto produto) {
		if(produtoRepository.remover(produto)){
			return true;
		}
		return false;
	}

	@Override
	public LazyDataModel<Produto> consultaPaginada(Paginacao paginacao) {
		return new LazyDataModel<Produto>() {

			private static final long serialVersionUID = 1L;
			
			@Override
			public List<Produto> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				paginacao.setPrimeiroRegistro(first);
				paginacao.setQuantidadeRegistros(pageSize);
				paginacao.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				paginacao.setPropriedadeOrdenacao(sortField);
				
				setRowCount(produtoRepository.quantidadeFiltrados(paginacao));
				
				return produtoRepository.filtrados(paginacao);
			}
			
		};
	}

}
