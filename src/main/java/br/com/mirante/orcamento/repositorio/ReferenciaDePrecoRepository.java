package br.com.mirante.orcamento.repositorio;

import java.util.List;
import java.util.Map;

import br.com.mirante.orcamento.domain.Produto;

public interface ReferenciaDePrecoRepository {

	Map<String, Produto> recuperarProdutos(Integer mes, Integer ano, List<String> codigos);
}
