package com.jozias.food.domain.reporitory;

import java.util.List;

import com.jozias.food.domain.model.Cozinha;

public interface CozinhaRepository {
	
	List<Cozinha> listar();
	Cozinha porId(Long id);
	List<Cozinha> consultarPorNome(String nome);
	Cozinha adicionar(Cozinha cozinha);
	void remover(Long id);
	void atualizar(Cozinha cozinha);
	
}
