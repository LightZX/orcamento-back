package br.com.mirante.orcamento.domain;

public class Produto {
	
	private String origem;
	private String codigo;
	private String descricao;
	private String unidade;
	private Float valor;
	public Produto(String origem, String codigo, String descricao, String unidade, Float valor) {
		super();
		this.origem = origem;
		this.codigo = codigo;
		this.descricao = descricao;
		this.unidade = unidade;
		this.valor = valor;
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
	public String getUnidade() {
		return unidade;
	}
	public Float getValor() {
		return valor;
	}
	
}