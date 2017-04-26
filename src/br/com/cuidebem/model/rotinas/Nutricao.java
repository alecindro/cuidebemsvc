package br.com.cuidebem.model.rotinas;

import java.util.Arrays;
import java.util.List;

public enum Nutricao{

	CAFE("Café da manhã",Arrays.asList("Aceitou bem","Aceitou parcialmente","Não aceitou")),
	ALMOCO("Almoço",Arrays.asList("Aceitou bem","Aceitou parcialmente","Não aceitou")),
	JANTAR("Jantar",Arrays.asList("Aceitou bem","Aceitou parcialmente","Não aceitou")),
	CEIA("Ceia",Arrays.asList("Aceitou bem","Aceitou parcialmente","Não aceitou")),
	LANCHE("Lanche",Arrays.asList("Aceitou bem","Aceitou parcialmente","Não aceitou")),
	LIQUIDOS("Ingestão de Líquidos",Arrays.asList("Pouco","Moderada","Grande"));
	
	private String descricao;
	private List<String> opcoes;
	
	private Nutricao(String descricao, List<String> opcoes) {
		this.descricao = descricao;
		this.opcoes = opcoes;
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
	
	
	
	
}
