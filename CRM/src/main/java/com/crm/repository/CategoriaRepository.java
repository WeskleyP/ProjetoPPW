package com.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crm.model.Categoria;
import com.crm.model.Cliente;
import com.crm.repository.query.CategoriaQuery;
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> , CategoriaQuery{
	@Query(value = "SELECT p FROM Categoria p WHERE p.nome LIKE %:nome%")
	List<Cliente> findCategoriaByName(@Param("nome") String nome);
}
