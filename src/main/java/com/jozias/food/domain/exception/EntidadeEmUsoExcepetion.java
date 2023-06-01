package com.jozias.food.domain.exception;

public class EntidadeEmUsoExcepetion extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EntidadeEmUsoExcepetion(String mensagem) {
		super(mensagem);
	}

}
