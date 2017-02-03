package br.com.cuidebem.model;

public enum Patologias {

	  HA("Hipertens�o Arterial"),
	  DIABETE("Diabetes"),
	  HIV ("HIV"),
	  CARDIO("Cardiopatia (problemas cardiacos)"),
	  ASMA("Asma/bronquite"),
	  PNEUMOPATIA("Pneumopatia (enfizema, bronquite, pneumonia)"),
	  AVC("Acidente vascular cerebral"),
	  OBESIDADE("Obesidade"),
	  RENAL("Renal cr�nico"),
	  EPILEPSIA("Epilepsia"),
	  CANCER("Cancer"),
	  TRAQUE("Faz uso de Traqueostomia"),
	  OXIGEN("Faz uso de Oxigenioterapia"),
	  HEPATITE("Hepatite"),
	  NEURO("Problemas Neurol�gicos");
	
	private String descricao;
	
	private Patologias(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	public static Patologias find(String descricao){
		for(Patologias patologia : Patologias.values()){
		if(patologia.getDescricao().equals(descricao)){
			return patologia;
		}
		}
		return null;
	}
	 
}
