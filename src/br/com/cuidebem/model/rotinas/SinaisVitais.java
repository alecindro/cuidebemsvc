package br.com.cuidebem.model.rotinas;

public enum SinaisVitais{

	PRESSAOARTERIAL("Press�o Arterial"),
	FREQUENCIACARDIACA("Frequ�ncia Card�aca"),
	TEMPERATURA("Temperatura Corporal"),
	FREQUENCIARESPIRATORIA("Frequ�ncia Respirat�ria"),
	SATURACAOOXIGENIO("Satura��o de Oxig�nio (oximetria)"),
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
