package com.jozias.food.api.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jozias.food.domain.exception.EntidadeEmUsoExcepetion;
import com.jozias.food.domain.exception.EntidadeNaoEncontradaException;
import com.jozias.food.domain.model.Cozinha;
import com.jozias.food.domain.reporitory.CozinhaRepository;
import com.jozias.food.domain.service.CadastroCozinhaService;


@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	CadastroCozinhaService cadastroCozinhaService;
	
	@GetMapping(produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
	public List<Cozinha> listar(){
		return cozinhaRepository.findAll();
	}
	
	@GetMapping(value = "/{cozinhaId}")
	public ResponseEntity<Cozinha> buscar(@PathVariable("cozinhaId") Long id) {
		Optional<Cozinha> cozinha = cozinhaRepository.findById(id);
		ResponseEntity<Cozinha> retorno =  cozinha.isPresent()
				? ResponseEntity.ok(cozinha.get()) 
				: ResponseEntity.notFound().build();
		return retorno;
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public Cozinha adicionar(@RequestBody Cozinha cozinha) {
		return cadastroCozinhaService.salvar(cozinha);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cozinha>atualizar(@RequestBody Cozinha cozinha, @PathVariable Long id){
		Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(id);
		if(cozinhaAtual.isEmpty()) {
			 return ResponseEntity.notFound().build();
		}
		BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");
		Cozinha cozinhaSalva = cadastroCozinhaService.salvar(cozinhaAtual.get());
		return ResponseEntity.status(HttpStatus.OK).body(cozinhaSalva);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Cozinha>excluirCozinha(@PathVariable Long id) {
		try {
			cadastroCozinhaService.excluir(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeEmUsoExcepetion e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
