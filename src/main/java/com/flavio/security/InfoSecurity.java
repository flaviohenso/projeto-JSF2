package com.flavio.security;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Named
@RequestScoped
public class InfoSecurity {

	public String getUserName() {

		UsuarioSistema usuarioSistema = this.getUsuarioLogado();

		if (usuarioSistema != null) {
			return usuarioSistema.getUsuario().getNome();
		}else{
			return null;
		}
		
	}

	private UsuarioSistema getUsuarioLogado() {

		UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) FacesContext
				.getCurrentInstance().getExternalContext().getUserPrincipal();
		if (authenticationToken != null && authenticationToken.getPrincipal() != null) {
			return (UsuarioSistema) authenticationToken.getPrincipal();
		}
		return null;
	}

}
