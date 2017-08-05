package com.flavio.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.flavio.model.Authoritie;
import com.flavio.repository.AuthoritieRepository;
import com.flavio.util.Paginacao;

@RequestScoped
public class AuthoritieService implements GenericService<Authoritie>, Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AuthoritieRepository authoritieRepository;

	public AuthoritieService() {
	}
	
	@Override
	public List<Authoritie> listRepository() {
		return authoritieRepository.listAll();
	}

	@Override
	public boolean salvar(Authoritie authoritie) throws Exception {
		if(authoritieRepository.save(authoritie)){
			System.out.println("salvou com suscesso");
			return true;
		}else{
			System.out.println("Erro ao salvar!");
			return false;
		}
	}
	
	public List<Authoritie> buscarPorNome(String nome){
		return authoritieRepository.byNome("%"+nome+"%");
	}

	public boolean remover(Authoritie authoritie) {

		if(authoritieRepository.remover(authoritie)){
			return true;
		}
		return false;
	}
	
	public LazyDataModel<Authoritie> consultaPaginada(Paginacao paginacao) {
		return new LazyDataModel<Authoritie>() {

			private static final long serialVersionUID = 1L;
			
			@Override
			public List<Authoritie> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				paginacao.setPrimeiroRegistro(first);
				paginacao.setQuantidadeRegistros(pageSize);
				paginacao.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				paginacao.setPropriedadeOrdenacao(sortField);
				
				setRowCount(authoritieRepository.quantidadeFiltrados(paginacao));
				
				return authoritieRepository.filtrados(paginacao);
			}
			
		};
	}
	
}
