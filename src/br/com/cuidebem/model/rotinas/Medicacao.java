package br.com.cuidebem.model.rotinas;

public enum Medicacao{

	MEDICAO("Medica��es");
	
	private String descricao;

	
	private Medicacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
