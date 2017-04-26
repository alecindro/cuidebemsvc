package br.com.cuidebem.model.rotinas;

import java.util.Arrays;
import java.util.List;

public enum Respiratorio{

	TRAQUEAL("Aspira��o Traqueal",Arrays.asList("Oral","Nasal"),Arrays.asList("Pouco","Normal","Grande"),Arrays.asList("Seroide","Mucosa","Purulenta")),
	NEBULIZACAO("Nebuliza��o",Arrays.asList("Oral","Nasal"),Arrays.asList("Pouco","Normal","Grande"),Arrays.asList("Seroide","Mucosa","Purulenta"));
	
	private String descricao;
	private List<String> opcoes;
	private List<String> quantidade;
	private List<String> aspecto;
	
	private Respiratorio(String descricao, List<String> opcoes, List<String> quantidade, List<String> aspecto) {
		this.descricao = descricao;
		this.opcoes = opcoes;
		this.quantidade = quantidade;
		this.aspecto = aspecto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<String> getOpcoes() {
		return opcoes;
	}
	public void setOpcoes(List<String> opcoes) {
		this.opcoes = opcoes;
	}
	public List<String> getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(List<String> quantidade) {
		this.quantidade = quantidade;
	}
	public List<String> getAspecto() {
		return aspecto;
	}
	public void setAspecto(List<String> aspecto) {
		this.aspecto = aspecto;
	}
	
	
	
		
	
	
	
}
