package model.cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteValidacao {

	private static List<String> errosValidacao;

	public static List<String> validaCliente(Cliente cliente) {
		errosValidacao = new ArrayList<>();

		if (!cliente.getNome().equals("")) {
			if (cliente.getNome().length() < 5 || cliente.getNome().length() > 100)
				errosValidacao.add("* O Nome deve ter entre 5 e 100 caracteres.");
		} else {
			errosValidacao.add("* O Nome n�o foi informado.");
		}
		

		if (!cliente.getCpf().equals("")) {
			if (cliente.getCpf().length() < 11 || cliente.getCpf().length() > 11)
				errosValidacao.add("* O CPF deve conter 11 n�meros.");
		} else {
			errosValidacao.add("* O Cpf n�o foi informado.");
		}
		
		
		return errosValidacao;
	}
}
