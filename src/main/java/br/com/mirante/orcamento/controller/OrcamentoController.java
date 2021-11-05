package br.com.mirante.orcamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mirante.orcamento.domain.ItemOrcamento;
import br.com.mirante.orcamento.domain.Orcamento;
import br.com.mirante.orcamento.servico.DetalharOrcamentoServico;
import br.com.mirante.orcamento.servico.ExcluirOrcamentoServico;
import br.com.mirante.orcamento.servico.ListarOrcamentoServico;
import br.com.mirante.orcamento.servico.RecuperarIncosistenciasServico;
import br.com.mirante.orcamento.servico.AtualizarOrcamentoServico;
import br.com.mirante.orcamento.servico.CadastraOrcamentoServico;

@RestController
@RequestMapping("/orcamentos")
public class OrcamentoController {
	
	@Autowired
	private ListarOrcamentoServico servico;
	
	@Autowired
	private DetalharOrcamentoServico detalharServico;
	
	@Autowired
	private CadastraOrcamentoServico cadastrarServico;
	
	@Autowired
	private ExcluirOrcamentoServico excluirServico;
	
	@Autowired
	private AtualizarOrcamentoServico atualizarServico;
	
	@Autowired
	private RecuperarIncosistenciasServico inconsistenciaServico;
	
	@GetMapping
	public List<Orcamento> listarOrcamentos(){
		return servico.listarOrcamentos();
	}
	
	@GetMapping("/{id}")
	public Orcamento recuperarOrcamento(@PathVariable Integer id) {
		return detalharServico.recuperar(id);
	}
	
	@PostMapping
	public Orcamento cadastrarOrcamento(@RequestBody CadastrarOrcamentoRequest requisicao) {
		return cadastrarServico.cadastrar(requisicao.orcamento, requisicao.itens);
	}
	
	@DeleteMapping("/{id}")
	public void excluirOrcamento(@PathVariable Integer id) {
		excluirServico.excluir(id);
	}
	
	@PutMapping("/{id}")
	public void atualizarOrcamento(@PathVariable Integer id, @RequestBody Orcamento orcamento) {
		atualizarServico.atualizar(id,orcamento);
	}
	
	@GetMapping("/{id}/incosistencias")
	public List<String> recuperarInconsistencias(@PathVariable Integer id){
		return inconsistenciaServico.recuperarInconsistencias(id);
	}
}

class CadastrarOrcamentoRequest {
	Orcamento orcamento;
	List<ItemOrcamento> itens;
	
	public Orcamento getOrcamento() {
		return orcamento;
	}
	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}
	public List<ItemOrcamento> getItens() {
		return itens;
	}
	public void setItens(List<ItemOrcamento> itens) {
		this.itens = itens;
	}
}