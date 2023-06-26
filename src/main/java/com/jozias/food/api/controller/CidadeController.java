package com.jozias.food.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jozias.food.domain.exception.EntidadeNaoEncontradaException;
import com.jozias.food.domain.model.Cidade;
import com.jozias.food.domain.reporitory.CidadeRepository;
import com.jozias.food.domain.service.CadastroCidadeService;


@RestController//formata o tipo da resposta
@Controller //diz que é um controller
@RequestMapping(value = "/cidades")
public class CidadeController {
	
	private static final String ENTIDADE_COM_O_ID_S_NÃO_ENCONTRADA = "Entidade com o id %s não encontrada";
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroCidadeService cadastroCidadeService;
	
	@PostMapping()
	public ResponseEntity<?> salvarCidade(@RequestBody Cidade cidade) {
		return ResponseEntity.status(HttpStatus.CREATED).body(cadastroCidadeService.salvarCidade(cidade));
	}
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<Cidade> listar(){
		return cidadeRepository.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> pesquisarCidade(@PathVariable(name = "id") Long id) {
		Cidade Cidade = cadastroCidadeService.pesquisarCidade(id);
		if(Cidade == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format(ENTIDADE_COM_O_ID_S_NÃO_ENCONTRADA, id));
		}
		return ResponseEntity.ok(Cidade);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarRecurso(@PathVariable Long id, @RequestBody Cidade Cidade){
		try {
			Cidade CidadeResponse = cadastroCidadeService.atualizarRecurso(id, Cidade);
			return ResponseEntity.ok(CidadeResponse);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		try {
			cadastroCidadeService.deletar(id);
			return ResponseEntity.ok(id);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
