package com.flavio.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.flavio.model.Authoritie;
import com.flavio.repository.AuthoritieRepository;

@RequestScoped
public class AuthoritieService implements GenericService<Authoritie>{

	@Inject
	private AuthoritieRepository authoritieRepository;

	public AuthoritieService() {
	}
	
	@Override
	public List<Authoritie> listRepository() {
		return authoritieRepository.listAll();
	}

	@Override
	public void salvar(Authoritie authoritie) throws Exception {
		if(authoritieRepository.save(authoritie)){
			System.out.println("salvou com suscesso");
		}else{
			System.out.println("Erro ao salvar!");
		}
	}
	
	public List<Authoritie> buscarPorNome(String nome){
		return authoritieRepository.byNome("%"+nome+"%");
	}
	
}
