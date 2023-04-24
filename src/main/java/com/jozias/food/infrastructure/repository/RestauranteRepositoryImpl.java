package com.jozias.food.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jozias.food.domain.model.Restaurante;
import com.jozias.food.domain.reporitory.RestauranteRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepository {

	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public List<Restaurante> listar(){
		return manager.createQuery("from Restaurante", Restaurante.class)
			.getResultList();
	}
	
	@Transactional
	@Override
	public Restaurante adicionar(Restaurante Restaurante) {
		return manager.merge(Restaurante);
	}
	
	@Transactional
	@Override
	public Restaurante porId(Long id) {
		return manager.find(Restaurante.class, id);
	}
	
	@Transactional
	@Override
	public void remover(Restaurante Restaurante) {
		manager.remove(porId(Restaurante.getId()));
	}
	
}
