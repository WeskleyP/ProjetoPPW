package com.crm.repository;

import com.crm.model.RolePermissao;
import com.crm.model.RolePermissaoId;
import com.crm.repository.query.RolePermissaoQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RolePermissaoRepository extends JpaRepository<RolePermissao, RolePermissaoId>, RolePermissaoQuery {

}
