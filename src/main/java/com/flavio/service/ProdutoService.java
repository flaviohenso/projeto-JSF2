package com.flavio.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.flavio.model.Produto;
import com.flavio.repository.ProdutoRepository;
import com.flavio.util.Paginacao;

@ViewScoped
public class ProdutoService implements GenericService<Produto>, Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private ProdutoRepository produtoRepository;

	@Override
	public List<Produto> listRepository() {
		return produtoRepository.listAll();
	}

	@Override
	public boolean salvar(Produto produto) throws Exception {
		if (produtoRepository.save(produto)) {
			return true;
		} 
		return false;
	}

	public Produto produtoByID(Long id) {
		
		if (id != null)
			return produtoRepository.BuscarPorID(id);
		
		return null;
	}

	@Override
	public boolean remover(Produto produto) {
		if (produtoRepository.remover(produto)) {
			return true;
		}
		return false;
	}

	@Override
	public LazyDataModel<Produto> consultaPaginada(Paginacao paginacao) {
		return new LazyDataModel<Produto>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Produto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
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
