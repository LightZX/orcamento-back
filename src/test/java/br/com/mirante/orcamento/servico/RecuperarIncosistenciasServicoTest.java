package br.com.mirante.orcamento.servico;

//import static org.mockito.Mockito.when;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.mirante.orcamento.domain.ItemOrcamento;
//import br.com.mirante.orcamento.domain.Orcamento;
//import br.com.mirante.orcamento.domain.Produto;
import br.com.mirante.orcamento.repositorio.ItemOrcamentoRepositoryJPA;
import br.com.mirante.orcamento.repositorio.OrcamentoRepository;
import br.com.mirante.orcamento.repositorio.ReferenciaDePrecoRepository;

@SpringBootTest
public class RecuperarIncosistenciasServicoTest {

	@Autowired
	private RecuperarIncosistenciasServico recuperarIncosistenciasServico;
	
	@MockBean
	private OrcamentoRepository orcamentoRepository;
	
	@MockBean
	private ItemOrcamentoRepositoryJPA itensRepositorio;
	
	@MockBean
	private ReferenciaDePrecoRepository referenciaPrecoRepository;
	
	/*@BeforeAll
	public void setup() {
		when(orcamentoRepository.recuperar(5)).thenReturn(new Orcamento("", 1, 2010, 550, null));
		when(itensRepositorio.listarPorIdOrcamento(5)).thenReturn(itens);
		when(referenciaPrecoRepository.recuperarProdutos(1, 2020, codigos)).thenReturn(new HashMap<String, Produto>());
	}*/
	
	public void naoDeveGerarInconsistencia() {
		List<String> inconsistencias = recuperarIncosistenciasServico.recuperarInconsistencias(5);
		Assertions.assertEquals(inconsistencias.size(), 0);
	}
	
	@Test
	public void deveGerarInconsistenciaQuantidadeZero() {
		RecuperarIncosistenciasServico servico = new RecuperarIncosistenciasServico();
		List<String> inconsistencias = new ArrayList<>();
		ItemOrcamento i = new ItemOrcamento(null, null, null, 0, null, 0, 0);
		servico.verificarInconsistenciaQuantidadeZero(inconsistencias, i, 5);
		Assertions.assertEquals(inconsistencias.get(0), "O item número 5 possui quantidade igual a zero.");
	}
	
	@Test
	public void naoDeveGerarInconsistenciaQuantidadeZero() {
		RecuperarIncosistenciasServico servico = new RecuperarIncosistenciasServico();
		List<String> inconsistencias = new ArrayList<>();
		ItemOrcamento i = new ItemOrcamento(null, null, null, 0, null, 0.5f, 0);
		servico.verificarInconsistenciaQuantidadeZero(inconsistencias, i, 5);
		Assertions.assertEquals(inconsistencias.size(), 0);
	}
}
