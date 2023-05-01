package com.jozias.food.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jozias.food.domain.model.FormaPagamento;
import com.jozias.food.domain.reporitory.FormaPagamentoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {

	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public List<FormaPagamento> listar(){
		return manager.createQuery("from FormaPagamento", FormaPagamento.class)
			.getResultList();
	}
	
	@Transactional
	@Override
	public FormaPagamento adicionar(FormaPagamento formaPagamento) {
		return manager.merge(formaPagamento);
	}
	
	@Transactional
	@Override
	public FormaPagamento porId(Long id) {
		return manager.find(FormaPagamento.class, id);
	}
	
	@Transactional
	@Override
	public void remover(FormaPagamento formaPagamento) {
		manager.remove(porId(formaPagamento.getId()));
	}
	
}
