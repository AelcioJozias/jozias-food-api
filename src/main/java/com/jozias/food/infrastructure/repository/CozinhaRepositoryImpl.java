package com.jozias.food.infrastructure.repository;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jozias.food.domain.model.Cozinha;
import com.jozias.food.domain.reporitory.CozinhaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {

	private static final String NOME = "nome";
	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public List<Cozinha> listar(){
		return manager.createQuery("from Cozinha", Cozinha.class)
			.getResultList();
	}
	
	
	
	@Transactional
	@Override
	public Cozinha adicionar(Cozinha cozinha) {
		return manager.merge(cozinha);
	}
	
	@Transactional
	@Override
	public Cozinha porId(Long id) {
		return manager.find(Cozinha.class, id);
	}
	
	@Transactional
	@Override
	public void remover(Long id) {//isso aqui não ficou legal, é melhor passar apenas o id, nao a instancia de cozinha
		Cozinha cozinha = porId(id);
		if(cozinha == null) {
			throw new EmptyResultDataAccessException(1);
		}
		manager.remove(porId(cozinha.getId()));
	}


	@Transactional
	@Override
	public void atualizar(Cozinha cozinha) {
		manager.merge(cozinha);
	}



	@Override
	public List<Cozinha> consultarPorNome(String nome) {
		return manager.createQuery("from Cozinha where nome like :nome", Cozinha.class)
				.setParameter(NOME, "%".concat(nome).concat("%"))
				.getResultList();
	}
	
}
