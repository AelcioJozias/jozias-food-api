package com.jozias.food.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jozias.food.domain.exception.EntidadeDeAssociacaoNaoExisteException;
import com.jozias.food.domain.exception.EntidadeNaoEncontradaException;
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
		
		if (retornaTrueSeNull(restaurante)) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(restaurante);
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante){
		try {
			Restaurante restauranteSaved = cadastroRestauranteService.salvar(restaurante);
			return ResponseEntity.ok().body(restauranteSaved);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarCadastroRestaurante(@PathVariable(name = "id") Long id, @RequestBody Restaurante restaurante){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(cadastroRestauranteService.atualizarCadastroRestaurante(id, restaurante));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (EntidadeDeAssociacaoNaoExisteException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> atualizarValoresIndividuaisDoRecurso(@PathVariable Long id,
			@RequestBody Map<String, Object>camposParaAtualizar){
		
		try {
			Restaurante restauranteAtual = cadastroRestauranteService.pesquisarRestaurantePorId(id);
			if (retornaTrueSeNull(restauranteAtual)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(String.format("Não foi encontrado um restaurante com o id: %s", id));
			}
			ObjectMapper objectMapper = new ObjectMapper();
			Restaurante restauranteOrigem = objectMapper.convertValue(camposParaAtualizar, Restaurante.class);
			camposParaAtualizar.forEach((key, value) -> {
				Field field = ReflectionUtils.findField(Restaurante.class, key);
				field.setAccessible(true);
				var novoValor = ReflectionUtils.getField(field, restauranteOrigem);
				ReflectionUtils.setField(field, restauranteAtual, novoValor);
			});
			return ResponseEntity.ok(cadastroRestauranteService.atualizarCadastroRestaurante(id, restauranteAtual)); 
		} catch (IllegalArgumentException e) {
			e.getMessage();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	private boolean retornaTrueSeNull(Restaurante restauranteAtual) {
		return restauranteAtual == null;
	}
	
}
