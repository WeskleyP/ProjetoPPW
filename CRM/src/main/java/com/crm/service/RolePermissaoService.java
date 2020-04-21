package com.crm.service;

import com.crm.model.Role;
import com.crm.model.RolePermissao;
import com.crm.model.RolePermissaoId;
import com.crm.repository.filter.RoleFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RolePermissaoService extends GenericService<RolePermissao, RolePermissaoId> {
	
}
