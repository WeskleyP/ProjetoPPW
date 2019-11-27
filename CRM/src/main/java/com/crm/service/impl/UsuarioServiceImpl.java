package com.crm.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.Cliente;
import com.crm.model.Usuario;
import com.crm.repository.UsuarioRepository;
import com.crm.repository.filter.UsuarioFilter;
import com.crm.service.UsuarioService;
import com.crm.service.exceptions.EmailClienteExistente;
@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public Usuario saveUsuario(Usuario usuario) {
		Optional<Usuario> optionalUsuario = findUsuarioByEmail(usuario.getEmailUsuario());
		if(optionalUsuario.isPresent()) {
			throw new EmailClienteExistente("Email já cadastrado");
		}
		if(usuario.getPassword().equals(usuario.getContraSenha())) {
			return null;
		}
		String password = passwordEncoder().encode(usuario.getPassword());
		usuario.setPassword(password);
		usuario.setAtivo(true);
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario update(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public void remove(Usuario usuario) {
		usuarioRepository.deleteById(usuario.getIdUsuario());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findUsuarioById(Long id) {
		return usuarioRepository.getOne(id);
	}

	@Override
	public List<Usuario> findUsuarioByName(String nome) {
		return usuarioRepository.findUsuarioByName(nome);
	}

	@Override
	public Page<Usuario> listaUsuarioComPaginacao(UsuarioFilter usuarioFilter, Pageable pageable) {
		return usuarioRepository.listaUsuarioComPaginacao(usuarioFilter, pageable);
	}

	@Override
	public Optional<Usuario> findUsuarioByEmail(String email) {
		return usuarioRepository.findUsuarioByEmail(email);
	}

}
