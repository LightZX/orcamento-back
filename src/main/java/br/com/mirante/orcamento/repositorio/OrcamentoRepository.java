package br.com.mirante.orcamento.repositorio;

import java.util.List;

import br.com.mirante.orcamento.domain.Orcamento;

public interface OrcamentoRepository {

	int obterMaiorId();
	void salvar(Orcamento orcamento);
	List<Orcamento> listar();
	Orcamento recuperar(int id);
	void excluir(Integer id);

}