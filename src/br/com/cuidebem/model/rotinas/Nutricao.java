package br.com.cuidebem.model.rotinas;

import java.util.Arrays;
import java.util.List;

public enum Nutricao{

	CAFE("Caf� da manh�",Arrays.asList("Aceitou bem","Aceitou parcialmente","N�o aceitou")),
	ALMOCO("Almo�o",Arrays.asList("Aceitou bem","Aceitou parcialmente","N�o aceitou")),
	JANTAR("Jantar",Arrays.asList("Aceitou bem","Aceitou parcialmente","N�o aceitou")),
	CEIA("Ceia",Arrays.asList("Aceitou bem","Aceitou parcialmente","N�o aceitou")),
	LANCHE("Lanche",Arrays.asList("Aceitou bem","Aceitou parcialmente","N�o aceitou")),
	LIQUIDOS("Ingest�o de L�quidos",Arrays.asList("Pouco","Moderada","Grande"));
	
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
