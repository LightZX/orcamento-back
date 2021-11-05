package br.com.mirante.orcamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.mirante.orcamento.domain.ItemOrcamento;
import br.com.mirante.orcamento.servico.DetalharOrcamentoServico;

@RestController
public class ItemOrcamentoController {
	
	@Autowired
	private DetalharOrcamentoServico servicoDetalhar;
	
	@GetMapping("/orcamentos/{id}/itens-orcamento")
	public List<ItemOrcamento> recuperarItens(@PathVariable Integer id){
		return servicoDetalhar.recuperarItens(id);
	}
}
