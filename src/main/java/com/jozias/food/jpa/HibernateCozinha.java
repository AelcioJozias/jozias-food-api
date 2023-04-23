package com.jozias.food.jpa;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jozias.food.domain.model.Cozinha;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;

@Component
public class HibernateCozinha {
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Cozinha> listar(){
		return manager.createQuery("from Cozinha", Cozinha.class)
			.getResultList();
	}
	
	@Transactional
	public Cozinha adicionar(Cozinha cozinha) {
		return manager.merge(cozinha);
	}
	
	public Cozinha buscar(Long id) {
		return manager.find(Cozinha.class, id);
	}
	
	@Transactional
	public void excluir(Cozinha cozinha) {
		manager.remove(buscar(cozinha.getId()));
	}
}
