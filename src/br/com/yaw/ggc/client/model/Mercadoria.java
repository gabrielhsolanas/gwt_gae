package br.com.yaw.ggc.client.model;

import java.io.Serializable;

import com.google.gwt.i18n.client.NumberFormat;

public class Mercadoria implements Serializable {

	private Long id;
	
	private String nome;
	
	private String descricao;
	
	private Integer quantidade;
	
	private Double preco;
	
	public Mercadoria(){}
	
	public Mercadoria(Long id, String nome, String descricao, Integer quantidade, Double preco) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	@Override
	public String toString() {
		return id + " - " + nome + " - " + descricao;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		
		if ((obj == null) || (obj.getClass() != this.getClass())) {
			return false;
		}
		
		Mercadoria outro = (Mercadoria) obj;
		boolean equal = (id != null && id.equals(outro.id)) 
				|| (nome != null && nome.equals(outro.nome))
				|| (descricao != null && descricao.equals(outro.descricao));
		return equal;
	}
	
	@Override
	public int hashCode() {
		int hash = 17;
		
		hash = (31 * hash) + (id == null ? 0 : id.intValue());
		hash = (31 * hash) + (nome == null ? 0 : nome.hashCode());
		hash = (31 * hash) + (descricao == null ? 0 : descricao.hashCode());
		
		return hash;
	}
	
	public static String convertPrecoToString(double preco) {
		NumberFormat numberFmt = NumberFormat.getFormat("#,##0.00");
		return numberFmt.format(preco);
	}
	
	public static double formatStringToPreco(String strPreco) {
		NumberFormat numberFmt = NumberFormat.getFormat("#,##0.00");
		 return numberFmt.parse(strPreco);
	}
	
}
