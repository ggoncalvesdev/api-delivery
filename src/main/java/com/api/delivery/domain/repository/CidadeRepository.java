package com.api.delivery.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.delivery.domain.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	
	// Resouver o problema N+1 dos outros repositórios
	@Query("from Cidade c join fetch c.estado")
	List<Cidade> findAll();

}
