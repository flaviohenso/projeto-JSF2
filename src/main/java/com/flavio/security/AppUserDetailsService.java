package com.flavio.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.flavio.model.Authoritie;
import com.flavio.model.Usuario;
import com.flavio.repository.UsuarioRepository;
import com.flavio.util.cdi.CDIServiceLocator;

public class AppUserDetailsService implements UserDetailsService {

	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UsuarioRepository usuarioRepository = CDIServiceLocator.getBean(UsuarioRepository.class);
		Usuario usuario = usuarioRepository.porEmail(email);
		
		UsuarioSistema user = null;
		
		if(usuario != null){
			user = new UsuarioSistema(usuario, getAuthorities(usuario));
		}
		
		return user;
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Usuario usuario) {
		List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<SimpleGrantedAuthority>();
		
		for (Authoritie authoritie : usuario.getAuthorities()) {
			simpleGrantedAuthorities.add(new SimpleGrantedAuthority(authoritie.getNome()));
		}
		
		return simpleGrantedAuthorities;
	}

}
