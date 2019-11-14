package com.crm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.crm.model.Usuario;
import com.crm.repository.filter.UsuarioFilter;

public interface UsuarioService {
	Usuario saveUsuario(Usuario usuario);
	Usuario update(Usuario usuario);
	void remove(Usuario usuario);
	List<Usuario> findAll();
	Usuario findUsuarioById(Long id);
	List<Usuario> findUsuarioByName(String nome);
	Page<Usuario> listaUsuarioComPaginacao(UsuarioFilter usuarioFilter,Pageable pageable);
	Optional<Usuario> findUsuarioByEmail(String email);
}
