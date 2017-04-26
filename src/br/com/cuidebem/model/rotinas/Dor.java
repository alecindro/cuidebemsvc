package br.com.cuidebem.model.rotinas;

public enum Dor{

	DOR("Dor");
	
	private String descricao;

	
	private Dor(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
