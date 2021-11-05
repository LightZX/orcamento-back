package br.com.mirante.orcamento.servico;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mirante.orcamento.domain.ItemOrcamento;
import br.com.mirante.orcamento.domain.Orcamento;
import br.com.mirante.orcamento.domain.Produto;
import br.com.mirante.orcamento.repositorio.ItemOrcamentoRepositoryJPA;
import br.com.mirante.orcamento.repositorio.OrcamentoRepository;
import br.com.mirante.orcamento.repositorio.ReferenciaDePrecoRepository;

@Service
public class RecuperarIncosistenciasServico {
	
	@Autowired
	private ItemOrcamentoRepositoryJPA itensRepositorio;
	
	@Autowired
	private ReferenciaDePrecoRepository referenciaPrecoRepository;
	
	@Autowired
	private OrcamentoRepository orcamentoRepository;
	
	public List<String> recuperarInconsistencias(Integer idOrcamento){
		List<String> inconsistencias = new ArrayList<>();
		Orcamento orcamento = orcamentoRepository.recuperar(idOrcamento);
		List<ItemOrcamento> itens = itensRepositorio.listarPorIdOrcamento(idOrcamento);
		
		Map<String, Produto> produtos = referenciaPrecoRepository.recuperarProdutos(
			orcamento.getMes(), 
			orcamento.getAno(), 
			itens.stream().map(ItemOrcamento::getCodigo).toList()
		);
		
		for (int index = 0; index < itens.size(); index++) {
			ItemOrcamento itemOrcamento = itens.get(index);
			int numeroItem = index + 1;
			
			inconsistensiasDoItem(inconsistencias, itemOrcamento, numeroItem, produtos);
		}
		 
		return inconsistencias;
	}
	
	void inconsistensiasDoItem(List<String> inconsistencias, ItemOrcamento itemOrcamento, int numeroItem, Map<String, Produto> produtos) {
		verificarInconsistenciaQuantidadeZero(inconsistencias, itemOrcamento, numeroItem);
		
		verificarInconsistenciaValorTotalCalculado(inconsistencias, itemOrcamento, numeroItem);
		
		verificarInconsistenciaPresencaReferenciaPreco(inconsistencias, itemOrcamento, numeroItem, produtos);
	}

	void verificarInconsistenciaValorTotalCalculado(List<String> inconsistencias, ItemOrcamento itemOrcamento,
			int numeroItem) {
		if(itemOrcamento.getValorTotalCalculado() != itemOrcamento.getValorTotalInformado()) {
			inconsistencias.add("O valor total do item de id " + numeroItem
					+ " deveria ser " + itemOrcamento.getValorTotalCalculado() 
					+ " mas foi " + itemOrcamento.getValorTotalInformado());
		}
	}

	void verificarInconsistenciaPresencaReferenciaPreco(List<String> inconsistencias, ItemOrcamento itemOrcamento,
			int numeroItem, Map<String, Produto> produtos) {
		if(produtos.containsKey(itemOrcamento.getCodigo())) {
			Produto p = produtos.get(itemOrcamento.getCodigo());
			
			if(!p.getUnidade().equals(itemOrcamento.getUnidade())) {
				inconsistencias.add("A unidade do item número " + numeroItem +
						" está em (" + itemOrcamento.getUnidade() + ") diverge  da unidade de refêrencia que é " +
						p.getUnidade()
				);
			}
			
			var valorTotalCalculadoReferencia = p.getValor() * itemOrcamento.getQuantidade();
			
			if(itemOrcamento.getValorTotalCalculado() > valorTotalCalculadoReferencia) {
				var valorSobrepreco = itemOrcamento.getValorTotalCalculado() - valorTotalCalculadoReferencia;
				var percentual = valorSobrepreco / valorTotalCalculadoReferencia * 100;
				inconsistencias.add("O item número " + numeroItem + 
						" possui sobrepreço de " + percentual + "%"
				);
			}
			
		} else {
			inconsistencias.add("O item número " + numeroItem + " não possui uma referência de preço válida.");
		}
	}

	void verificarInconsistenciaQuantidadeZero(List<String> inconsistencias, ItemOrcamento itemOrcamento, int numeroItem) {
		if (itemOrcamento.getQuantidade() == 0) {
			inconsistencias.add("O item número " + numeroItem + " possui quantidade igual a zero.");
		}
	}
}
