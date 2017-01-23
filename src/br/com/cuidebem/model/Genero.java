package br.com.cuidebem.model;

public enum Genero {
	
	MASCULINO(1,"MASCULINO"),
	FEMININO(2,"FEMININO");
	private Integer value;
	private String descricao;
	
	Genero(Integer value,String descricao){
		this.value = value;
		this.descricao = descricao;
	}
	
	public Integer getValue() {
		return value;
	}

	

	public String getDescricao() {
		return descricao;
	}

	
	
	
}
