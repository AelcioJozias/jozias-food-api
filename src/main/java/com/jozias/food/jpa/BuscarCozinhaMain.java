package com.jozias.food.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.jozias.food.JoziasFoodApiApplication;
import com.jozias.food.domain.model.Cozinha;
import com.jozias.food.domain.reporitory.CozinhaRepository;

public class BuscarCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(JoziasFoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

		CozinhaRepository CozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
		
		Cozinha cozinha2 = CozinhaRepository.porId(1L);
		//importante, se vc instanciar o objeto sem ao invés de fazer a busca no banco, 
		// vai dar uma exception por causa que o a instancia está em estado transiente
		
		CozinhaRepository.remover(cozinha2);
	}

}
