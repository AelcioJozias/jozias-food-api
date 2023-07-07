package com.jozias.food.domain.reporitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jozias.food.domain.model.Cozinha;

public interface CozinhaRepository extends JpaRepository<Cozinha, Long>{
	
	List<Cozinha> findByNomeContaining(String nome);
}
