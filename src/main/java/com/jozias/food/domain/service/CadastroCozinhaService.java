package com.jozias.food.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jozias.food.domain.exception.EntidadeEmUsoExcepetion;
import com.jozias.food.domain.exception.EntidadeNaoEncontradaException;
import com.jozias.food.domain.model.Cozinha;
import com.jozias.food.domain.reporitory.CozinhaRepository;

@Service
public class CadastroCozinhaService {

	@Autowired
	CozinhaRepository cozinhaRepository;
	
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.adicionar(cozinha);
	}
	
	public void excluir(Long id) {
		try {
			cozinhaRepository.remover(id);
		}
		
		catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(
					"Não existe um cadastro de cozinha com código", id));
		}
		
		catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoExcepetion(
					String.format("Cozinha de código %d não pode ser removida",
							id));
		}
	}
	
}
