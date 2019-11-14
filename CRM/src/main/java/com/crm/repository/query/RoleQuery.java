package com.crm.repository.query;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.crm.model.Role;
import com.crm.repository.filter.RoleFilter;

public interface RoleQuery {
	Page<Role> listaRoleComPaginacao(RoleFilter roleFilter, Pageable pageable);
	
}
