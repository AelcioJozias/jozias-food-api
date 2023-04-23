package com.jozias.food.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.jozias.food.JoziasFoodApiApplication;
import com.jozias.food.domain.model.Cozinha;

public class ConsultaCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(JoziasFoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

		HibernateCozinha beanCozinha=  applicationContext.getBean(HibernateCozinha.class);
		
		//se usar apenas assim, buscando antes, também dá ruim, porque vai buscar e depois fechar a conexão
		//por isso deixeia busca também implmentada dentro do excluir
		Cozinha cozinha = beanCozinha.buscar(2L);
		
		System.out.println(cozinha.getNome());
		
	}

}
