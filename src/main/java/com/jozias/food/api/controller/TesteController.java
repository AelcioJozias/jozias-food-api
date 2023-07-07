package com.jozias.food.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jozias.food.domain.model.Cozinha;
import com.jozias.food.domain.model.Restaurante;
import com.jozias.food.domain.reporitory.CozinhaRepository;
import com.jozias.food.domain.reporitory.RestauranteRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {

	@Autowired
	CozinhaRepository cozinhaRepository;
	
	@Autowired
	RestauranteRepository restauranteRepository;
	
	  @GetMapping("/cozinhas/por-nome") public List<Cozinha>
	  listarCozinhaPorNome(@RequestParam String nome){ 
		  return cozinhaRepository.findByNomeContaining(nome); 
	  }
	 
	  @GetMapping("/cozinhas/por-cozinha")
	  List<Restaurante>listarCozinhaPorIdDeRestaurante(Long id){
		  return restauranteRepository.findByCozinhaId(id);
	  }
	  
	  @GetMapping("/restaurates/por-cozinha-unica")
	  Optional<Restaurante>procurarPrimeiroRestaurentePorIdDaCozinha(Long id){
		  return restauranteRepository.findFirstByCozinhaId(id);
	  }
	  
	  @GetMapping("/restaurante/consultar-por-nome")
	  List<Restaurante>consultarPorNome(String nome, Long id){
		  return restauranteRepository.consultarPorNome(nome, id);
	  }
	  
	  
}
