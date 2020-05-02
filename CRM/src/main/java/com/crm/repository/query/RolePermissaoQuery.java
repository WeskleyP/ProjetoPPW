package com.crm.repository.query;

import com.crm.model.RolePermissao;
import com.crm.repository.filter.RolePermissaoFiltro;

import java.util.List;

public interface RolePermissaoQuery {
	List<RolePermissao> findRolePermissaoEscopoFiltro(RolePermissaoFiltro rolePermissaoFiltro);
}
