package com.crm.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.Cliente;
import com.crm.model.Usuario;
import com.crm.model.dto.UserDTO;
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
	//@Secured("hasRole('ROLE_ADMINISTRADOR')")
	//@PreAuthorize("hasPermission('CADASTRO_USUARIO','WRITE')")
	public Usuario saveUsuario(Usuario usuario) throws Exception {
		Optional<Usuario> optionalUsuario = findUsuarioByEmail(usuario.getEmailUsuario());
		if(optionalUsuario.isPresent()) {
			throw new EmailClienteExistente("Email já cadastrado");
		}
		if(!(usuario.getPassword().equals(usuario.getContraSenha()))) {
			throw new Exception();
		}
		String password = passwordEncoder().encode(usuario.getPassword());
		usuario.setPassword(password);
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
	
	@Override
	public Usuario fromDto(UserDTO objDto) {
		return new Usuario(objDto.getIdUsuario(),objDto.getUsername(),objDto.getEmailUsuario());
	}

}
