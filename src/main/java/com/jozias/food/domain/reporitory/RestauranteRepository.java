package com.jozias.food.domain.reporitory;

import java.util.List;

import com.jozias.food.domain.model.Restaurante;

public interface RestauranteRepository {
	
	List<Restaurante> listar();
	Restaurante porId(Long id);
	Restaurante adicionar(Restaurante Restaurante);
	void remover(Restaurante Restaurante);
	
}
