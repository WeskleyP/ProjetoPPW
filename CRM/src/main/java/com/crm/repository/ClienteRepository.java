package com.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crm.model.Cliente;
import com.crm.repository.query.ClienteQuery;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>,ClienteQuery{
	
	@Query(value = "SELECT p FROM Cliente p WHERE p.nome LIKE %:nome%")
	List<Cliente> findClienteByName(@Param("nome") String nome);
	
	@Query("SELECT p FROM Cliente p JOIN Telefone t ON p.id = t.cliente.id WHERE p.id =:id")
	Cliente findClienteByIdAndTelefone(@Param("id") Long id);
	
}
