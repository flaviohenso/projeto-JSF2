package com.flavio.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.flavio.model.Usuario;
import com.flavio.util.cdi.CDIServiceLocator;

public class AppUserDetailsService implements UserDetailsService {

//	Usuario usuario = CDIServiceLocator.getBean(Usuario.class);
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return null;
	}

}
