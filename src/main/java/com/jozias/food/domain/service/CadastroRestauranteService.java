package com.jozias.food.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jozias.food.domain.model.Restaurante;
import com.jozias.food.domain.reporitory.RestauranteRepository;

@Service
public class CadastroRestauranteService {
	
	@Autowired
	RestauranteRepository restauranteRepository;
	
	public List<Restaurante> listarRestaurante(){
		return restauranteRepository.listar();
	}
	
	public Restaurante pesquisarRestaurantePorId(Long id) {
		return restauranteRepository.porId(id);
	}
}
