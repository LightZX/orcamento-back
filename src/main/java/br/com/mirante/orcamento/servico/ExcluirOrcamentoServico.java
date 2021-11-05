package br.com.mirante.orcamento.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mirante.orcamento.repositorio.ItemOrcamentoRepositoryJPA;
import br.com.mirante.orcamento.repositorio.OrcamentoRepository;

@Service
public class ExcluirOrcamentoServico {
	
	@Autowired
	private OrcamentoRepository repositorio;
	
	@Autowired
	private ItemOrcamentoRepositoryJPA itensRepositorio;
	
	@Transactional
	public void excluir(Integer id) {
		itensRepositorio.deleteByIdOrcamento(id);
		repositorio.excluir(id);
	}
}
