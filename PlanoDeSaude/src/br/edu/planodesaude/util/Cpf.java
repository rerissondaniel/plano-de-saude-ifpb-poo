package br.edu.planodesaude.util;


//Referência: http://www.geradorcpf.com/algoritmo_do_cpf.htm
public class Cpf {
	private String cpf;

	private Cpf(String cpf) {
		this.cpf = cpf;
	}

	public static Cpf getInstance(String str) throws IllegalArgumentException{
		/*
		 * Retorna um novo CPF a partir da String de entrada.Caso o CPF seja
		 */
		if (validaCpf(str))
			return new Cpf(str);
		throw new IllegalArgumentException("CPF Inválido");
	}

	private static boolean validaCpf(String str) {
		/*	
		 * Assume que o CPF está em um formato válido. i. e. xxx.yyy.zzz-dd
		 */
		String aux = str.substring(0, 3) + str.substring(4, 7)
				+ str.substring(8, 11);
		int[] multi = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
		int sum = 0;
		for (int i = 1; i < 10; i++)
			sum += multi[i] * Integer.parseInt(aux.substring(i - 1, i));
		if (sum % 11 < 2)
			aux += "0";
		else
			aux += String.format("%d", 11 - (sum % 11));
		sum = 0;
		for (int i = 0; i < 10; i++)
			sum += multi[i] * Integer.parseInt(aux.substring(i, i + 1));
		if (sum % 11 < 2)
			aux += 0;
		else
			aux += String.format("%d", 11 - (sum % 11));
		if (str.charAt(str.length() - 1) == aux.charAt(aux.length() - 1)
				&& str.charAt(str.length() - 2) == aux.charAt(aux.length() - 2))
			return true;
		return false;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String newCpf) throws IllegalArgumentException{
		if (validaCpf(newCpf)) {
			this.cpf = newCpf;
		}
		throw new IllegalArgumentException("CPF Inválido");
	}

	@Override
	public String toString() {
		return this.getCpf();
	}
}
