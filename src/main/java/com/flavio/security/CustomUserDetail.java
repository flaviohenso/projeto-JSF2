/**
 * projeto-web : 20 de set de 2017 
 */
package com.flavio.security;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author flavio
 *
 */
public class CustomUserDetail implements UserDetails{
	
	
	private static final long serialVersionUID = 1L;

	private UsuarioSistema UsuarioSistema;

	Set<GrantedAuthority> authorities = null;
	
	public UsuarioSistema getUsuarioSistema() {
		return UsuarioSistema;
	}

	public void setUsuarioSistema(UsuarioSistema usuarioSistema) {
		UsuarioSistema = usuarioSistema;
	}
	
	public void setAuthorities(Set<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}


	@Override
	public String getPassword() {
		return getUsuarioSistema().getPassword();
	}

	@Override
	public String getUsername() {
		return getUsuarioSistema().getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return getUsuarioSistema().isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return getUsuarioSistema().isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return getUsuarioSistema().isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return getUsuarioSistema().isEnabled();
	}
	
}
