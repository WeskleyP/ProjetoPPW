package com.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crm.model.Cliente;
import com.crm.model.Produto;
import com.crm.repository.query.ClienteQuery;
import com.crm.repository.query.ProdutoQuery;
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> , ProdutoQuery{
	@Query(value = "SELECT p FROM Produto p WHERE p.descricao LIKE %:descricao%")
	List<Produto> findProdutoByDesc(@Param("descricao") String descricao);
}
