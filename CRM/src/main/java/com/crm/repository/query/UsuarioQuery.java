package com.crm.repository.query;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.crm.model.Usuario;
import com.crm.repository.filter.UsuarioFilter;

public interface UsuarioQuery {
	Page<Usuario> listaUsuarioComPaginacao(UsuarioFilter usuarioFilter, Pageable pageable);
	Optional<Usuario> findUsuarioByEmail(String email);
}
