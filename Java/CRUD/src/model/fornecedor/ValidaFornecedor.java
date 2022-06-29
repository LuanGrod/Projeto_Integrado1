package model.fornecedor;

import java.util.ArrayList;
import java.util.List;

import controller.FornecedorController;

public class ValidaFornecedor {
	private static List<String> errosValidacao;
	private static List<Fornecedor> fornecedores;
	
	public static List<String> validaFornecedor(Fornecedor fornecedor){
		errosValidacao = new ArrayList<>();
		
		fornecedores = new ArrayList<>();
		fornecedores = new FornecedorController().consultaFornecedores();
		
		for(Fornecedor f : fornecedores) {
			if(fornecedor.getCnpj().equals(f.getCnpj())) {
				errosValidacao.add("* CNPJ já existente.");
				return errosValidacao;
			}
		}
		
		if (!fornecedor.getCnpj().equals("")) {
			if (fornecedor.getCnpj().length() < 6 || fornecedor.getCnpj().length() > 100)
				errosValidacao.add("* O CNPJ deve ter mais de 6 caracteres");
		} else {
			errosValidacao.add("* O CNPJ não foi informado.");
		}
		
		if (!fornecedor.getNome().equals("")) {
			if (fornecedor.getNome().length() < 5 || fornecedor.getNome().length() > 100)
				errosValidacao.add("* O Nome deve ter entre 5 e 100 caracteres.");
		} else {
			errosValidacao.add("* O Nome não foi informado.");
		}
		
		
		return errosValidacao;

	}

}
