package model.categoria;

import java.util.ArrayList;
import java.util.List;

import controller.ProdutoController;

public class ValidaCategoria {
	private static List<String> errosValidacao;
	private static List<Categoria> categorias;
	
	public static List<String> validaCategoria(Categoria c){
		errosValidacao = new ArrayList<>();
		categorias = new ArrayList<>();
		
		categorias = new ProdutoController().recuperaCategorias();
		
		/*for(Categoria cat : categorias) {
			if (c.getDescricao().equals(cat.getDescricao())) {
				errosValidacao.add("* Categoria já existente.");
			}
		}*/
		
		if(c.getDescricao().length() < 2) {
			errosValidacao.add("* O nome da categoria deve ter pelo menos 2 caracteres");
		}
		return errosValidacao;
	}
}
