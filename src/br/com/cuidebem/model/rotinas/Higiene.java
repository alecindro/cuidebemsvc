package br.com.cuidebem.model.rotinas;

public enum Higiene{

	BANHO("Realiza��o de Banho"),
	ORAL("Higiene Oral");
	
	private String descricao;

	private Higiene(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
