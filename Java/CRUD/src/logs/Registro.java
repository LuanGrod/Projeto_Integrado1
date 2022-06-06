package logs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Registro {
	List<String> lista = new ArrayList<>();

	public void emitirRegistro(List<String> lista) {
		try {
			FileWriter fw = new FileWriter("D:\\IFSP\\3° SEMESTRE\\BD2A3\\CRUD\\Projeto_Integrado1\\Java\\CRUD\\src\\logs\\logs.txt", true);
			BufferedWriter bf = new BufferedWriter(fw);
			for(int i=0 ; i<lista.size() ; i++) {
				bf.write(lista.get(i));
			}
			
			bf.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<String> adicionarRegistro(String log) {
		lista.add(log);
		return lista;
	}
}
