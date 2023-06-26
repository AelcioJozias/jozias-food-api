package com.jozias.food.domain.reporitory;

import java.util.List;

import com.jozias.food.domain.model.Estado;

public interface EstadoRepository {
	
	List<Estado> listar();
	Estado porId(Long id);
	Estado adicionar(Estado cozinha);
	void remover(Estado cozinha);
	
}
