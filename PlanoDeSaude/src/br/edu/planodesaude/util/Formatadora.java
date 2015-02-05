package br.edu.planodesaude.util;

public class Formatadora {
	
	public static String format(String str, int line){
		
		str += "  ";
		
		String res = "";
		int ant = 0, len = str.length(), aux = 0;
		
		for(int i = 0; i < len - 1; i++){
			
			if(str.charAt(i) == ' '){
				if(aux + (i - ant) <= line){
					res += str.substring(ant, i);
				}
				else{
					res += "\n";
					aux = 0;
					res += str.substring(ant + 1, i);
				}
				aux += i - ant;
				ant = i;
			}
		}
		
		return res;
	}
}
