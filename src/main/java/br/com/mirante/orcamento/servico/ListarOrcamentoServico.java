package br.com.mirante.orcamento.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mirante.orcamento.domain.ItemOrcamento;
import br.com.mirante.orcamento.domain.Orcamento;
import br.com.mirante.orcamento.servico.ListarOrcamentoServico;
import br.com.mirante.orcamento.repositorio.ItemOrcamentoRepositoryJPA;
import br.com.mirante.orcamento.repositorio.OrcamentoRepository;

@Service
public class ListarOrcamentoServico {
	
	@Autowired
	private OrcamentoRepository repository;
	
	@Autowired
	private ItemOrcamentoRepositoryJPA itensRepositorio;
	
	public List<Orcamento> listarOrcamentos(){
		List<Orcamento> orcamentos = repository.listar();
		orcamentos = orcamentos.stream().map(this::atribuirValorTotal).toList();
		return repository.listar();
	}
	
	private Orcamento atribuirValorTotal(Orcamento orcamento) {
		orcamento.setValorTotalCalculado(calcularValorTotalOrcamento(orcamento));
		return orcamento;
	}
	
	private Float calcularValorTotalOrcamento(Orcamento orcamento) {
		List <ItemOrcamento> itens = itensRepositorio.listarPorIdOrcamento(orcamento.getId());
		Float valorTotalOrcamento = 0F;
		
		for (ItemOrcamento itemOrcamento : itens) {
			valorTotalOrcamento += itemOrcamento.getValorTotalCalculado();
		}
		
		return valorTotalOrcamento;
	}
}
