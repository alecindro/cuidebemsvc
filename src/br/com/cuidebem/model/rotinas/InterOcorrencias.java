package br.com.cuidebem.model.rotinas;

public enum InterOcorrencias{

	INTEROCORRENCIAS("Interocorr�ncias");
	
	private String descricao;
	
	private InterOcorrencias(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
