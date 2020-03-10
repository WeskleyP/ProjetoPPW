package com.crm.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.Role;
import com.crm.repository.RoleRepository;
import com.crm.repository.filter.RoleFilter;
import com.crm.service.RoleService;
import com.crm.service.exceptions.EntidadeEmUsoException;
import com.crm.service.exceptions.EntidadeNaoCadastradaException;
import com.crm.service.exceptions.RoleNaoCadastradaException;
@Service
@Transactional
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleRepository roleRepository;
	@Override
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Role update(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public void remove(Role role) {
		try {
			roleRepository.deleteById(role.getIdRole());
		}catch(DataIntegrityViolationException e){
			throw new EntidadeEmUsoException(
					String.format("A role de código %d não pode ser removida, pois está em uso!", role.getIdRole())
				);
		} catch (EmptyResultDataAccessException e) {
			throw new RoleNaoCadastradaException(role.getIdRole());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Role findRoleById(Long id) {
		return roleRepository.findById(id)
					.orElseThrow(() -> new RoleNaoCadastradaException(id));
	}

	@Override
	public List<Role> findRoleByName(String roleNome) {
		return roleRepository.findRoleByName(roleNome);
	}

	@Override
	public Page<Role> listaRoleComPaginacao(RoleFilter roleFilter, Pageable pageable) {
		return roleRepository.listaRoleComPaginacao(roleFilter, pageable);
	}

}
