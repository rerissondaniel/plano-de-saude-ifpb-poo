package br.edu.planodesaude.util;

public enum Mes {
	JANEIRO(1, "Janeiro", "jan"),
	FEVEREIRO(2, "Fevereiro", "fev"),
	MARCO(3, "Março", "mar"),
	ABRIL(4, "Abril", "abr"),
	MAIO(5, "Maio", "maio"),
	JUNHO(6, "Junho", "jun"),
	JULHO(7, "Julho", "jun"),
	AGOSTO(8, "Agosto", "ago"),
	SETEMBRO(9, "Setembro", "set"),
	OUTUBRO(10, "Outubro", "out"),
	NOVEMBRO(11, "Novembro", "nov"),
	DEZEMBRO(12, "Dezembro", "dez");
	
	private final String rotulo1;
	private final String rotulo2;
	private final int valor;
	
	Mes(int val, String str1, String str2){
		valor = val;
		rotulo1 = str1;
		rotulo2 = str2;
	}
	public int getValor() {
		return valor;
	}
	
	public String getRotulo1() {
		return rotulo1;
	}
	
	public String getRotulo2() {
		return rotulo2;
	}
	
	@Override
	public String toString(){
		return this.getRotulo1();
	}
}
