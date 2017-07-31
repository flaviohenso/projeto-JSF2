package com.flavio.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.flavio.model.Categoria;
import com.flavio.repository.CategoriaRepository;
import com.flavio.util.Paginacao;

public class CategoriasService implements GenericService<Categoria>{
	
	@Inject
	private CategoriaRepository categoriaRepository;

	@Override
	public List<Categoria> listRepository() {
		
		return categoriaRepository.listAll();
	}

	@Override
	public boolean salvar(Categoria categoria) throws Exception {
		if(categoriaRepository.save(categoria)){
			System.out.println("salvou com suscesso");
			return true;
		}else{
			System.out.println("Erro ao salvar!");
			return false;
		}
	}

	@Override
	public boolean remover(Categoria categoria) {
		if(categoriaRepository.remover(categoria)){
			return true;
		}
		return false;
	}

	@Override
	public LazyDataModel<Categoria> consultaPaginada(Paginacao paginacao) {
		return new LazyDataModel<Categoria>() {

			private static final long serialVersionUID = 1L;
			
			@Override
			public List<Categoria> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
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
