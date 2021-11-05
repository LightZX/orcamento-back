package br.com.mirante.orcamento.repositorio;

import java.util.List;

import br.com.mirante.orcamento.domain.ItemOrcamento;

public interface ItemRepository {
	
	void excluir(Integer codigoItem);
	void salvar(List<ItemOrcamento> itens);
	void excluirItens(Integer IdOrcamento);

}