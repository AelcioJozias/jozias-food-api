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

	private static final String ID = "id";
	private static final String NÃO_EXISTE_UM_RECURSO_COM_O_ID_S = "Não existe um recurso com o id %S ";
	@Autowired
	private CidadeRepository cidadeRepository;

	public List<Cidade> listarCidade() {
		return cidadeRepository.findAll();
	}

	public Cidade pesquisarCidade(Long id) {
		return cidadeRepository.findById(id).get();
	}

	public Cidade atualizarRecurso(Long id, Cidade Cidade) {
		Cidade CidadeAtual = cidadeRepository.findById(id).orElseThrow(() -> {
			throw new EntidadeNaoEncontradaException(String.format(NÃO_EXISTE_UM_RECURSO_COM_O_ID_S, id));
		});
		BeanUtils.copyProperties(Cidade, CidadeAtual, ID);
		return cidadeRepository.save(CidadeAtual);
	}

	public void deletar(Long id) {
		Cidade cidadeARemover = cidadeRepository.findById(id).orElseThrow(() -> {
			throw new EntidadeNaoEncontradaException(String.format(NÃO_EXISTE_UM_RECURSO_COM_O_ID_S, id));
		});
		cidadeRepository.delete(cidadeARemover);
	}

	public Cidade salvarCidade(Cidade Cidade) {
		return cidadeRepository.save(Cidade);
	}
}
