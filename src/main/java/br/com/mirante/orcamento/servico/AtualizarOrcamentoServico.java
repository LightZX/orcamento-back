package br.com.mirante.orcamento.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mirante.orcamento.domain.Orcamento;
import br.com.mirante.orcamento.repositorio.OrcamentoRepository;

@Service
public class AtualizarOrcamentoServico {
	
	@Autowired
	private OrcamentoRepository repositorio;

	@Transactional
	public void atualizar(Integer id, Orcamento orcamento) {
		if(orcamento.getId() != null && !orcamento.getId().equals(id)) {
			throw new RuntimeException("Dados inválidos.");
		}
		
		Orcamento orcamentoRecuperado = repositorio.recuperar(id);
		
		if(orcamento.getDescricao() != null) {
			orcamentoRecuperado.setDescricao(orcamento.getDescricao());
		}
		
		if(orcamento.getMes() != null) {
			orcamentoRecuperado.setMes(orcamento.getMes());
		}
		
		if(orcamento.getAno() != null) {
			orcamentoRecuperado.setAno(orcamento.getAno());
		}
		
		if(orcamento.getValorTotalInformado() != null) {
			orcamentoRecuperado.setValorTotalInformado(orcamento.getValorTotalInformado());
		}
		
		repositorio.salvar(orcamentoRecuperado);
	}

}
