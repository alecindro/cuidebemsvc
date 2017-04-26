package br.com.cuidebem.model.rotinas;

public enum SinaisVitais{

	PRESSAOARTERIAL("Pressão Arterial"),
	FREQUENCIACARDIACA("Frequência Cardíaca"),
	TEMPERATURA("Temperatura Corporal"),
	FREQUENCIARESPIRATORIA("Frequência Respiratória"),
	SATURACAOOXIGENIO("Saturação de Oxigênio (oximetria)"),
	CONTROLEGLICEMIA("Controle da Glicemia");
	
private String descricao;
	
	
	private SinaisVitais(String descricao) {
		this.descricao = descricao;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
    
	public SinaisVitais[] getValues(){
		return SinaisVitais.values();
	}
	

}
