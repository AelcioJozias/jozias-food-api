package com.jozias.food.domain.reporitory;

import java.util.List;

import com.jozias.food.domain.model.Cidade;

public interface CidadeRepository {
	
	List<Cidade> listar();
	Cidade porId(Long id);
	Cidade adicionar(Cidade cidade);
	void remover(Cidade cidade);
	
}
