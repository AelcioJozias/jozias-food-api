package com.jozias.food.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jozias.food.domain.model.Restaurante;
import com.jozias.food.domain.service.CadastroRestauranteService;

@Controller
@RequestMapping("/restaurantes")
public class RestauranteController {
	
	@Autowired
	CadastroRestauranteService cadastroRestauranteService;
	
	@GetMapping
	public ResponseEntity<List<Restaurante>> listarRestaurantes(){
		List<Restaurante> restaurantes = cadastroRestauranteService.listarRestaurante();
		if(restaurantes.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(restaurantes);
		}
		return ResponseEntity.ok(restaurantes);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Restaurante> pesquisarRestaurantePorId(
			@PathVariable(value = "id") Long idRestaurante){
		
		Restaurante restaurante = cadastroRestauranteService.
				pesquisarRestaurantePorId(idRestaurante);
		
		if (restaurante == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(restaurante);
	}
	
	
	
}
