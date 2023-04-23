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

		CadastroCozinha cadastroCozinha=  applicationContext.getBean(CadastroCozinha.class);
		
		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("Arabe");
		cozinha2.setId(1L);
		//usando o o merge do hibernate ele também consegue alterar se identificar um id sendo passado
		cadastroCozinha.adicionar(cozinha2);
		
	}

}
