package com.crm.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.crm.model.Role;
import com.crm.repository.filter.RoleFilter;

public interface RoleService {
	Role saveRole(Role role);
	Role update(Role role);
	void remove(Role role);
	List<Role> findAll();
	Role findRoleById(Long id);
	List<Role> findRoleByName(String roleNome);
	Page<Role> listaRoleComPaginacao(RoleFilter roleFilter, Pageable pageable);
	
}
