package br.com.mirante.orcamento.repositorio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.com.mirante.orcamento.domain.Produto;

@Repository
public class ReferenciaPrecoRepositoryMemoria implements ReferenciaDePrecoRepository{

	@Override
	public Map<String, Produto> recuperarProdutos(Integer mes, Integer ano, List<String> codigos) {
		Map<String, Produto> produtos = new HashMap<>();
		produtos.put("1234", new Produto("IBGE", "1234", "Arroz", "T", 2F));
		return produtos;
	}
}
