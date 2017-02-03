package br.com.cuidebem.model;

public enum TypeUser {
	
	RESPONSAVEL(1),
	CUIDADOR(2),
	AMBOS(3);
	
	private int order;

	private TypeUser(int order) {
		this.order = order;
	}

	public int getOrder() {
		return order;
	}
	
	

}
