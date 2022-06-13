package model.usuario;

import java.util.ArrayList;
import java.util.List;


public class UsuarioValidacao {
	private static List<String> errosValidacao;
	
	public static List<String> validaUsuario(Usuario usuario){
		errosValidacao = new ArrayList<>();
		
		
		
		if(usuario.getLogin().length()<5 ) {
			errosValidacao.add("* O login deve ao menos 5 caracteres");
		}
		
		if(usuario.getSenha().length()<5 ) {
			errosValidacao.add("* A senha deve ao menos 5 caracteres");
		}
		
		if(usuario.getCpf().length()<11 ) {
			errosValidacao.add("* O CPF deve ao menos 11 caracteres");
		}
		
		if(usuario.getNome().length()<5 ) {
			errosValidacao.add("* O nome deve ao menos 5 caracteres");
		}
		
		if(usuario.getTelefone().length()<5 ) {
			errosValidacao.add("* O telefone deve ao menos 5 caracteres");
		}
		
		if(usuario.getEmail().length()<5 ) {
			errosValidacao.add("* O email deve ao menos 5 caracteres");
		}
		
		if(usuario.getRua().length()<5 ) {
			errosValidacao.add("* A rua deve ao menos 5 caracteres");
		}
		
		if(usuario.getBairro().length()<5 ) {
			errosValidacao.add("* O bairro deve ao menos 5 caracteres");
		}
		
		if(usuario.getCidade().equals("") ) {
			errosValidacao.add("* Insira a cidade");
		}
		
		if(usuario.getCep().length()<5 ) {
			errosValidacao.add("* O cep deve ao menos 5 caracteres");
		}
		
		if(usuario.getEstado().length()<2 ) {
			errosValidacao.add("* O estado deve ao menos 2 caracteres");
		}
		

		
		
	return errosValidacao;	
}
}