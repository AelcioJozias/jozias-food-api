package com.jozias.food.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jozias.food.domain.exception.EntidadeNaoEncontradaException;
import com.jozias.food.domain.model.Estado;
import com.jozias.food.domain.reporitory.EstadoRepository;
import com.jozias.food.domain.service.CadastroEstadoService;


@RestController//formata o tipo da resposta
@Controller //diz que é um controller
@RequestMapping(value = "/estados")
public class EstadoController {
	
	private static final String ENTIDADE_COM_O_ID_S_NÃO_ENCONTRADA = "Entidade com o id %s não encontrada";
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstadoService;
	
	@PostMapping()
	public ResponseEntity<?> salvarEstado(@RequestBody Estado estado) {
		return ResponseEntity.status(HttpStatus.CREATED).body(cadastroEstadoService.salvarEstado(estado));
	}
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<Estado> listar(){
		return estadoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> pesquisarEstado(@PathVariable(name = "id") Long id) {
		Estado estado = cadastroEstadoService.pesquisarEstado(id);
		if(estado == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format(ENTIDADE_COM_O_ID_S_NÃO_ENCONTRADA, id));
		}
		return ResponseEntity.ok(estado);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarRecurso(@PathVariable Long id, @RequestBody Estado estado){
		try {
			Estado estadoResponse = cadastroEstadoService.atualizarRecurso(id, estado);
			return ResponseEntity.ok(estadoResponse);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		try {
			cadastroEstadoService.deletar(id);
			return ResponseEntity.ok(id);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
