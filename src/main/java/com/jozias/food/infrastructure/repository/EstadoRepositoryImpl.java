package com.jozias.food.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jozias.food.domain.model.Cozinha;
import com.jozias.food.domain.model.Estado;
import com.jozias.food.domain.reporitory.CozinhaRepository;
import com.jozias.food.domain.reporitory.EstadoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class EstadoRepositoryImpl implements EstadoRepository {

	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public List<Estado> listar(){
		return manager.createQuery("from Estado", Estado.class)
			.getResultList();
	}
	
	@Transactional
	@Override
	public Estado adicionar(Estado estado) {
		return manager.merge(estado);
	}
	
	@Transactional
	@Override
	public Estado porId(Long id) {
		return manager.find(Estado.class, id);
	}
	
	@Transactional
	@Override
	public void remover(Estado estado) {
		manager.remove(porId(estado.getId()));
	}
	
}
