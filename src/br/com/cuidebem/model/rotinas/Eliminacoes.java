package br.com.cuidebem.model.rotinas;

import java.util.Arrays;
import java.util.List;

public enum Eliminacoes{

	URINA("Urina",Arrays.asList("Pouco","Normal","Grande"),Arrays.asList("Clara","Escura","Avermelhada")),
	FEZES("Fezes",Arrays.asList("Pouco","Normal","Grande"),Arrays.asList("L�quido","Pastoso","Endurecido","Fecaloma,Sangue")),
	VOMITO("Vomito",Arrays.asList("Pouco","Normal","Grande"),Arrays.asList("Restos Alimentares","Amarelo","Sangue","Borra de Caf�"));
	
	private String descricao;
	private List<String> quantidade;
	private List<String> aspecto;
	private Eliminacoes(String descricao, List<String> quantidade, List<String> aspecto) {
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.aspecto = aspecto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<String> getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(List<String> quantidade) {
		this.quantidade = quantidade;
	}
	public List<String> getAspecto() {
		return aspecto;
	}
	public void setAspecto(List<String> aspecto) {
		this.aspecto = aspecto;
	}
	
	
}
