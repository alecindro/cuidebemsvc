package br.com.cuidebem.model.rotinas;

import java.util.Arrays;
import java.util.List;

public enum BemEstar{

	ACORDAR("Sono - Acordou",Arrays.asList("Dormiu bem","Agitou","Insonia")),
	DORMIR("Sono - Dormiu",Arrays.asList("Normal","A base de rem�dios")),
	PASSEIOS("Passeios",Arrays.asList("Iniciou","Retornou")),
	CONSCIENCIA("N�vel de Consci�ncia",Arrays.asList("Acordado", "Sonolento","Irresponsivo")),
	HUMOR("Humor",Arrays.asList("Alegre","Calmo","Ap�tico","Depressivo","Agressivo","Agitado"));
	
	private String descricao;
	private List<String> opcoes;

	private BemEstar(String descricao, List<String> opcoes) {
		this.descricao = descricao;
		this.opcoes = opcoes;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<String> getOpcoes() {
		return opcoes;
	}
	public void setOpcoes(List<String> opcoes) {
		this.opcoes = opcoes;
	}
	
	
	
}
