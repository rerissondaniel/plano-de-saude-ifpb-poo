package br.edu.planodesaude.exceptions;

public class DoctorNotFoundException extends Throwable{

	private static final long serialVersionUID = 1L;

	public DoctorNotFoundException(String str){
		super(str);
	}
}
