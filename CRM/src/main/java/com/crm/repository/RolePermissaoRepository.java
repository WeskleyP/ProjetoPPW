package com.crm.repository;

import com.crm.model.Categoria;
import com.crm.model.RolePermissao;
import com.crm.model.RolePermissaoId;
import com.crm.repository.query.CategoriaQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissaoRepository extends JpaRepository<RolePermissao, RolePermissaoId>{

}
