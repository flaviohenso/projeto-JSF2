package com.flavio.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.flavio.exception.CategoriaException;
import com.flavio.model.Categoria;
import com.flavio.repository.CategoriaRepository;
import com.flavio.util.Paginacao;

public class CategoriasService implements GenericService<Categoria>, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriaRepository categoriaRepository;

	@Override
	public List<Categoria> listRepository() {

		return categoriaRepository.listAll();
	}

	@Override
	public boolean salvar(Categoria categoria) throws Exception {

		if (categoriaRepository.save(categoria)) {
			return true;
		} else {
			System.out.println("Erro ao salvar!");
			return false;
		}

	}

	@Override
	public boolean remover(Categoria categoria) throws CategoriaException{
		/*
		 * remove a categoria se a categoria existir e se não estiver associada
		 * a nenhum produto
		 */
		if (categoria.getId() != null && !categoriaRepository.cascadeAll(categoria)) {
			if (categoriaRepository.remover(categoria)) {
				return true;
			}
		} else {
			throw new CategoriaException(categoria.getId() == null ? "Categoria não presente na base de dados" : 
				"Essa categoria não pode ser removida pois esta associada a um produto");
		}
		return false;
	}
	
	@Override
	public LazyDataModel<Categoria> consultaPaginada(Paginacao paginacao) {
		return new LazyDataModel<Categoria>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Categoria> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				paginacao.setPrimeiroRegistro(first);
				paginacao.setQuantidadeRegistros(pageSize);
				paginacao.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				paginacao.setPropriedadeOrdenacao(sortField);

				setRowCount(categoriaRepository.quantidadeFiltrados(paginacao));

				return categoriaRepository.filtrados(paginacao);
			}

		};
	}

}
