package com.jozias.food.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jozias.food.domain.model.Cidade;
import com.jozias.food.domain.reporitory.CidadeRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class CidadeRepositoryImpl implements CidadeRepository {

	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public List<Cidade> listar(){
		return manager.createQuery("from Cidade", Cidade.class)
			.getResultList();
	}
	
	@Transactional
	@Override
	public Cidade adicionar(Cidade cidade) {
		return manager.merge(cidade);
	}
	
	@Transactional
	@Override
	public Cidade porId(Long id) {
		return manager.find(Cidade.class, id);
	}
	
	@Transactional
	@Override
	public void remover(Cidade cidade) {
		manager.remove(porId(cidade.getId()));
	}
	
}
