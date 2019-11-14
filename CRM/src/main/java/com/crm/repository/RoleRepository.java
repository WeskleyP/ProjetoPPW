package com.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crm.model.Cliente;
import com.crm.model.Role;
import com.crm.repository.query.RoleQuery;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, RoleQuery {
	
	@Query(value = "SELECT p FROM Role p WHERE p.nomeRole LIKE %:nomeRole%")
	List<Role> findRoleByName(@Param("nomeRole") String nome);
}
