package com.jozias.food.domain.reporitory;

import java.util.List;

import com.jozias.food.domain.model.Permissao;

public interface PermissaoRepository {
	
	List<Permissao> listar();
	Permissao porId(Long id);
	Permissao adicionar(Permissao permissao);
	void remover(Permissao permissao);
}
