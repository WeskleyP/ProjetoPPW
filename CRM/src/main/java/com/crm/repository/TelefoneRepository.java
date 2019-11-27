package com.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crm.model.Telefone;
@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

	@Query(value = "SELECT t FROM Telefone t WHERE t.cliente.id =:id")
	List<Telefone> findTelefoneClienteById(@Param("id") Long id);
}
