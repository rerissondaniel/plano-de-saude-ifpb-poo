package br.edu.planodesaude.util;

//Referência: http://www.geradorcnpj.com/algoritmo_do_cnpj.htm
public class Cnpj {

	private String cnpj;

	public static Cnpj getInstance(String str) throws IllegalArgumentException {
		/*
		 * Retorna um novo CNPJ a partir da String de entrada, Caso o CNPJ seja
		 * válido
		 */
		if (validaCnpj(str))
			return new Cnpj(str);
		throw new IllegalArgumentException("CNPJ Inválido");
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) throws IllegalArgumentException {
		if (validaCnpj(cnpj))
			this.cnpj = cnpj;
		throw new IllegalArgumentException("CNPJ Inválido");
	}

	private Cnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public static boolean validaCnpj(String str) throws IllegalArgumentException {
		/*
		 * Assume que o CNPJ está em um formato válido. i. e. ww.xxx.yyy/0001-zz
		 */

		String aux = str.substring(0, 2) + str.substring(3, 6) + str.substring(7, 10) + str.substring(11, 15);

		int[] multi1 = { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
		int sum = 0;

		for (int i = 1; i < 13; i++) {
			sum += multi1[i - 1] * Integer.parseInt(aux.substring(i - 1, i));
		}

		if (sum % 11 < 2) {
			aux += "0";
		} else {
			aux += String.format("%d", 11 - (sum % 11));
		}

		int[] multi2 = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
		sum = 0;

		for (int i = 1; i < 14; i++) {
			sum += multi2[i - 1] * Integer.parseInt(aux.substring(i - 1, i));
		}

		if (sum % 11 < 2) {
			aux += "0";
		} else {
			aux += String.format("%d", 11 - (sum % 11));
		}

		if (aux.charAt(aux.length() - 1) == str.charAt(str.length() - 1)
				&& aux.charAt(aux.length() - 2) == str.charAt(str.length() - 2)) {
			return true;
		}

		return false;
	}
}
