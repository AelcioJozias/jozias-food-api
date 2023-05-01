package com.jozias.food.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jozias.food.domain.model.Permissao;
import com.jozias.food.domain.reporitory.PermissaoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class PermissaoRepositoryImpl implements PermissaoRepository {

	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public List<Permissao> listar(){
		return manager.createQuery("from Permissao", Permissao.class)
			.getResultList();
	}
	
	@Transactional
	@Override
	public Permissao adicionar(Permissao permissao) {
		return manager.merge(permissao);
	}
	
	@Transactional
	@Override
	public Permissao porId(Long id) {
		return manager.find(Permissao.class, id);
	}
	
	@Transactional
	@Override
	public void remover(Permissao permissao) {
		manager.remove(porId(permissao.getId()));
	}
	
}
