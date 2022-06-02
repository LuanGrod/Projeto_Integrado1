package br.edu.ifsp.model.cargo;

import java.util.ArrayList;
import java.util.List;


public class CargoValidacao {
	private static List<String> errosValidacao; // List para armazenar as mensagens de erro de valida��o.

	// Valida os dados informados conforme as regras abaixo.
	public static List<String> validaCargo(Cargo cargo){
		errosValidacao = new ArrayList<>();

		// Valida��o do campo Descricao.
		if (!cargo.getDescricao().equals("")) {
			if (cargo.getDescricao().length() < 3 || cargo.getDescricao().length() > 60)
				errosValidacao.add("* A Descricao deve ter entre 3 e 60 caracteres.");
		} else {
			errosValidacao.add("* A Descricao n�o foi informada.");
		}

		// Valida para ver se tem numeros no campo Descricao
		int flag = 0;
		for(int i=0 ; i<cargo.getDescricao().length() ; i++) {
			char c = cargo.getDescricao().charAt(i);
			if(Character.isDigit(c)) { flag++; }
		} if(flag != 0) {
			errosValidacao.add("* A Descricao n�o deve conter n�meros.");
		}

		return errosValidacao; // Retorna o ArrayList contendo as mensagens de erro de valida��o.
	}

}
