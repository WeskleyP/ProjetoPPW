package com.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crm.model.Usuario;
import com.crm.repository.query.UsuarioQuery;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioQuery{
	@Query(value = "SELECT p FROM Usuario p WHERE p.username LIKE %:nome%")
	List<Usuario> findUsuarioByName(@Param("nome") String nome);
}
