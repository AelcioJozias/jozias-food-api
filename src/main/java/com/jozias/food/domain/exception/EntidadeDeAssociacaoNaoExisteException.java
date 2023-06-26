package com.jozias.food.domain.exception;

public class EntidadeDeAssociacaoNaoExisteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntidadeDeAssociacaoNaoExisteException(String message) {
		super(message);
	}
}
