package com.jozias.food.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jozias.food.domain.exception.EntidadeNaoEncontradaException;
import com.jozias.food.domain.model.Estado;
import com.jozias.food.domain.reporitory.EstadoRepository;

@Service
public class CadastroEstadoService {
	
	private static final String NÃO_EXISTE_UM_RECURSO_COM_O_ID_S = "Não existe um recurso com o id %S ";
	@Autowired
	private EstadoRepository estadoRepository;
	
	public List<Estado> listarEstado(){
		return estadoRepository.findAll();
	}
	
	public Estado pesquisarEstado(Long id) {
		return estadoRepository.findById(id).get();
	}
	
	public Estado atualizarRecurso(Long id, Estado estado) {
		Estado estadoAtual = estadoRepository.findById(id).orElseThrow(()-> {
			throw new EntidadeNaoEncontradaException(String.format(NÃO_EXISTE_UM_RECURSO_COM_O_ID_S, id));
		});
		BeanUtils.copyProperties(estado, estadoAtual, "id");
		return estadoRepository.save(estadoAtual);
	}
	
	public void deletar(Long id) {
		Estado estadoARemover = estadoRepository.findById(id).orElseThrow(()->{
			throw new EntidadeNaoEncontradaException(String.format(NÃO_EXISTE_UM_RECURSO_COM_O_ID_S, id));
		});
		estadoRepository.delete(estadoARemover);
	}
	
	public Estado salvarEstado(Estado estado) {
		return estadoRepository.save(estado);
	}
}
