package com.jozias.food.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jozias.food.domain.exception.EntidadeDeAssociacaoNaoExisteException;
import com.jozias.food.domain.exception.EntidadeNaoEncontradaException;
import com.jozias.food.domain.model.Cozinha;
import com.jozias.food.domain.model.Restaurante;
import com.jozias.food.domain.reporitory.CozinhaRepository;
import com.jozias.food.domain.reporitory.RestauranteRepository;

@Service
public class CadastroRestauranteService {
	
	private static final String NÃO_HÁ_UMA_ENTIDADE_COM_O_ID = "Não há uma entidade com o id: %s.";

	private static final String NÃO_EXISTE_UMA_COZINHA_COM_O_ID = "Não existe uma cozinha com o id: %s";

	private static final String NÃO_FOI_ENCONTRADA_UMA_COZINHA_COM_O_ID = "Não foi encontrada uma cozinha com o id %s";

	@Autowired
	RestauranteRepository restauranteRepository;
	
	@Autowired
	CozinhaRepository cozinhaRepository;
	
	public List<Restaurante> listarRestaurante(){
		return restauranteRepository.listar();
	}
	
	public Restaurante pesquisarRestaurantePorId(Long id) {
		return restauranteRepository.porId(id);
	}
	
	public Restaurante salvar(Restaurante restaurante) {
			Long cozinhaId = restaurante.getCozinha().getId();
			Cozinha cozinha = cozinhaRepository.porId(cozinhaId);
			if(cozinha == null) {
				throw new EntidadeNaoEncontradaException(String.format(NÃO_FOI_ENCONTRADA_UMA_COZINHA_COM_O_ID,
						cozinhaId));
			}
		return restauranteRepository.adicionar(restaurante);
	}

	public Restaurante atualizarCadastroRestaurante(Long id, Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Restaurante restauranteAtual =  restauranteRepository.porId(id);
		lancaExcecaoCasoOResourceNaoExista(restauranteAtual, id);
		verificaSeExisteUmaCozinhaPeloId(cozinhaId);
		BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
		restauranteRepository.adicionar(restauranteAtual);
		return restauranteAtual;
	}

	private void verificaSeExisteUmaCozinhaPeloId(Long cozinhaId) {
		if(cozinhaRepository.porId(cozinhaId) == null) {
			throw new EntidadeDeAssociacaoNaoExisteException(String.format(NÃO_EXISTE_UMA_COZINHA_COM_O_ID, cozinhaId));
		}
	}

	private void lancaExcecaoCasoOResourceNaoExista(Restaurante restaurante,Long id) {
		if(restaurante == null) {
			throw new EntidadeNaoEncontradaException(String.format(NÃO_HÁ_UMA_ENTIDADE_COM_O_ID, id));
		}
	}
}
