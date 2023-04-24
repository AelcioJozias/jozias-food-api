package com.jozias.food.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.jozias.food.JoziasFoodApiApplication;
import com.jozias.food.domain.model.Cozinha;
import com.jozias.food.domain.reporitory.CozinhaRepository;

public class AlterarCozinha {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(JoziasFoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

		CozinhaRepository CozinhaRepository =  applicationContext.getBean(CozinhaRepository.class);
		
		List<Cozinha> cozinhas = CozinhaRepository.listar();
		
		cozinhas.forEach(e -> System.out.println(e.getNome()));
		
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Brasileira");
		
		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("Japonesa");
		
		CozinhaRepository.adicionar(cozinha1);
		CozinhaRepository.adicionar(cozinha2);
		
	}

}
