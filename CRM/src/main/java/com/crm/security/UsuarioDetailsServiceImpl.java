package com.crm.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.crm.model.Usuario;
import com.crm.service.UsuarioService;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioService.findUsuarioByEmail(email);
		if(!usuario.isPresent()) {
			throw new UsernameNotFoundException("Usuario não encontrado!" + email);
		}
		return new UsuarioSistema(usuario.get());
	}

}
