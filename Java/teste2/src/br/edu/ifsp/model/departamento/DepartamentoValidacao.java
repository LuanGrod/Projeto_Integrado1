package br.edu.ifsp.model.departamento;

import java.util.ArrayList;
import java.util.List;


public class DepartamentoValidacao {
	private static List<String> errosValidacao; // List para armazenar as mensagens de erro de validação.

	// Valida os dados informados conforme as regras abaixo.
	public static List<String> validaDepartamento(Departamento departamento){
		errosValidacao = new ArrayList<>();

		// Validação do campo NomeDepto.
		if (!departamento.getNomeDepto().equals("")) {
			if (departamento.getNomeDepto().length() < 3 || departamento.getNomeDepto().length() > 60)
				errosValidacao.add("* O Nome do departamento deve ter entre 3 e 60 caracteres.");
		} else {
			errosValidacao.add("* O Nome do departamento não foi informado.");
		}

		// Valida para ver se tem numeros no campo NomeDepto
		int flag = 0;
		for(int i=0 ; i<departamento.getNomeDepto().length() ; i++) {
			char c = departamento.getNomeDepto().charAt(i);
			if(Character.isDigit(c)) { flag++; }
		} if(flag != 0) {
			errosValidacao.add("* O Nome do departamento não deve conter números.");
		}

		return errosValidacao; // Retorna o ArrayList contendo as mensagens de erro de validação.
	}

}
