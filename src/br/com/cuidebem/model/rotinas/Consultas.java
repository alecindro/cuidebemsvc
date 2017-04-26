package br.com.cuidebem.model.rotinas;

public enum Consultas{

	CONSULTA("Consultas de Saude");
	
private String descricao;

	
	private Consultas(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	

}
