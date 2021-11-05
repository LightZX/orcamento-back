package br.com.mirante.orcamento.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mirante.orcamento.domain.ItemOrcamento;
import br.com.mirante.orcamento.domain.Orcamento;
import br.com.mirante.orcamento.repositorio.ItemOrcamentoRepositoryJPA;
import br.com.mirante.orcamento.repositorio.OrcamentoRepository;

@Service
public class CadastraOrcamentoServico {
	
	@Autowired
	private OrcamentoRepository repositorio;
	
	@Autowired
	private ItemOrcamentoRepositoryJPA itensRepositorio;
	
	@Transactional
	public Orcamento cadastrar(Orcamento orcamento,List<ItemOrcamento> itens) {
		int maiorId = repositorio.obterMaiorId();
		orcamento.setId(maiorId + 1);
		repositorio.salvar(orcamento);
		itens.forEach(item -> item.setOrcamento(orcamento));
		itensRepositorio.saveAll(itens);
		return orcamento;
	}
}
