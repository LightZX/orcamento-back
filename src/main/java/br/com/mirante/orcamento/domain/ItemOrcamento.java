package br.com.mirante.orcamento.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ITEM_ORCAMENTO")
public class ItemOrcamento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String origem, codigo, descricao;
	
	@Column(name = "VALOR_UNITARIO")
	private float valorUnitario;
	private String unidade;
	private float quantidade;
	
	@Column(name = "VALOR_TOTAL_INFORMADO")
	private float valorTotalInformado;
	
	@ManyToOne
	@JoinColumn(name = "ID_ORCAMENTO")
	private Orcamento orcamento;

	public ItemOrcamento(Integer id, String origem, String codigo, String descricaoItem, float valorUnitario, String unidadeMedida,
			float quantidade, float valorTotalInformado) {
		this(origem, codigo, descricaoItem, valorUnitario, unidadeMedida, quantidade, valorTotalInformado);
		this.id = id;
	}
	
	public ItemOrcamento(String origem, String codigo, String descricaoItem, float valorUnitario, String unidadeMedida,
			float quantidade, float valorTotalInformado) {

		super();
		this.origem = origem;
		this.codigo = codigo;
		this.descricao = descricaoItem;
		this.valorUnitario = valorUnitario;
		this.unidade = unidadeMedida;
		this.quantidade = quantidade;
		this.valorTotalInformado = valorTotalInformado;
	}

	protected ItemOrcamento() {
		
	}
	
	public Integer getId() {
		return id;
	}
	
	public boolean possuiInconsistencia() {
		var diferenca = getValorTotalCalculado() - valorTotalInformado;
		return diferenca > 0.009 || diferenca < -0.009;
	}

	public String getOrigem() {
		return origem;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public float getValorUnitario() {
		return valorUnitario;
	}

	public String getUnidade() {
		return unidade;
	}

	public float getQuantidade() {
		return quantidade;
	}

	public float getValorTotalInformado() {
		return valorTotalInformado;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento ;		
	}

	public Float getValorTotalCalculado() {
		return quantidade * valorUnitario;
	}
	
}