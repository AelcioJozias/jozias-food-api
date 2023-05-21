package com.jozias.food.api.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jozias.food.domain.model.Cozinha;
import com.jozias.food.domain.reporitory.CozinhaRepository;


@RestController
@Controller
@RequestMapping(value = "/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@GetMapping(produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
	public List<Cozinha> listar(){
		return cozinhaRepository.listar();
	}
	
	@GetMapping(value = "/{cozinhaId}")
	public Cozinha buscar(@PathVariable("cozinhaId") Long id) {
		System.out.println(id);
		return cozinhaRepository.porId(id);
	}
}
