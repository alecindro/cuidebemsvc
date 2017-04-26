package br.com.cuidebem.model.rotinas;

public enum Rotinas {
	
	SINALVITAL("Sinais Vitais",SinaisVitais.values()),
	RESPIRATORIO("Respirat�rio",Respiratorio.values()),
	NUTRICAO("Nutri��o",Nutricao.values()),
	MEDICACAO("Medica��o",Medicacao.values()),
	INTEROCORRENCIAS("Interocorr�ncias",InterOcorrencias.values()),
	HIGIENE("Higiene",Higiene.values()),
	ELIMINACOES("Elimina��es",Eliminacoes.values()),
	DOR("Dor",Dor.values()),
	CONSULTAS("Consultas",Consultas.values()),
	BEMESTAR("Bem Estar",BemEstar.values());

	private String descricao;
	private Enum[] tipo;
	public String getDescricao() {
		return descricao;
	}
	
	
	private Rotinas(String descricao, Enum[] tipo) {
		this.descricao = descricao;
		this.tipo = tipo;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Enum[] getTipo() {
		return tipo;
	}


	public void setTipo(Enum[] tipo) {
		this.tipo = tipo;
	}
	
	public Rotinas[] getValues(){
		return Rotinas.values();
	}
	
	
}
