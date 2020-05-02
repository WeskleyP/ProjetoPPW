package com.crm.repository;

import com.crm.model.Escopo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EscopoRepository extends JpaRepository<Escopo, Long>{

}
