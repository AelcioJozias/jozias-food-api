package com.jozias.food.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.jozias.food.JoziasFoodApiApplication;
import com.jozias.food.domain.model.Cozinha;

public class BuscarCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(JoziasFoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

		HibernateCozinha cadastroCozinha=  applicationContext.getBean(HibernateCozinha.class);
		
		Cozinha cozinha2 = cadastroCozinha.buscar(1L);
		//importante, se vc instanciar o objeto sem ao invés de fazer a busca no banco, 
		// vai dar uma exception por causa que o a instancia está em estado transiente
		
		cadastroCozinha.excluir(cozinha2);
	}

}
