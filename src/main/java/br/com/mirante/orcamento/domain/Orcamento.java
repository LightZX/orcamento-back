package br.com.mirante.orcamento.domain;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Orcamento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;
	private String descricao;
	private Integer mes, ano;

	@Column(name = "VALOR_TOTAL_INFORMADO")
	private Float valorTotalInformado;
	
	@Transient
	private Float valorTotalCalculado;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	protected Orcamento() {}
	
	public Orcamento(String descricao, int mes, int ano, float valorTotalInformado, List<ItemOrcamento> itensOrcamento) {
		super();
		this.descricao = descricao;
		this.mes = mes;
		this.ano = ano;
		
		this.valorTotalInformado = valorTotalInformado;
		
	}
	
	public boolean possuiInconsistencias() {
		return false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Float getValorTotalInformado() {
		return valorTotalInformado;
	}

	public void setValorTotalInformado(Float valorTotalInformado) {
		this.valorTotalInformado = valorTotalInformado;
	}
	
	public Float getValorTotalCalculado() {
		return valorTotalCalculado;
	}

	public void setValorTotalCalculado(Float valorTotalCalculado) {
		this.valorTotalCalculado = valorTotalCalculado;
	}
	
	public Float getDiferencaCalculo() {
		return valorTotalCalculado - valorTotalInformado;
	}

}