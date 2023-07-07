package com.jozias.food.domain.reporitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jozias.food.domain.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{
	
}
