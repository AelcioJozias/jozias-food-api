package com.jozias.food.domain.reporitory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jozias.food.domain.model.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
	
	List<Restaurante> findByNome(String id);
	
	List<Restaurante> findByCozinhaId(Long id);
	
	Optional<Restaurante> findFirstByCozinhaId(Long id);
	
	@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
	List<Restaurante>consultarPorNome(String nome,@Param("id") Long cozinhaId);
}
 