package com.jozias.food.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jozias.food.domain.exception.EntidadeNaoEncontradaException;
import com.jozias.food.domain.model.Cidade;
import com.jozias.food.domain.reporitory.CidadeRepository;

@Service
public class CadastroCidadeService {
	
	private static final String NÃO_EXISTE_UM_RECURSO_COM_O_ID_S = "Não existe um recurso com o id %S ";
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public List<Cidade> listarCidade(){
		return cidadeRepository.listar();
	}
	
	public Cidade pesquisarCidade(Long id) {
		return cidadeRepository.porId(id);
	}
	
	public Cidade atualizarRecurso(Long id, Cidade Cidade) {
		Cidade CidadeAtual = cidadeRepository.porId(id);
		if(CidadeAtual == null) {
			throw new EntidadeNaoEncontradaException(String.format(NÃO_EXISTE_UM_RECURSO_COM_O_ID_S, id));
		}
		BeanUtils.copyProperties(Cidade, CidadeAtual, "id");
		return cidadeRepository.adicionar(CidadeAtual);
	}
	
	public void deletar(Long id) {
		Cidade CidadeARemover = cidadeRepository.porId(id);
		if(CidadeARemover == null) {
			throw new EntidadeNaoEncontradaException(String.format(NÃO_EXISTE_UM_RECURSO_COM_O_ID_S, id));
		}
		cidadeRepository.remover(CidadeARemover);
	}
	
	public Cidade salvarCidade(Cidade Cidade) {
		return cidadeRepository.adicionar(Cidade);
	}
}
