package view.usuario;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.cargo.Cargo;
import model.usuario.Usuario;

@SuppressWarnings("serial")
public class ModeloTabelaUsuario extends AbstractTableModel { // A classe abstrata AbstractTableModel implementa a interface TableModel.
	// Array de nomes a serem exibidos no cabeçalho do JTable.
	private String[] colunas = { "Código", "Login", "Senha", "CPF", "Nome", "Telefone", "Email", "Rua", "Bairro", "Cidade", "CEP", "Estado", "Cargo"};
	private List<Usuario> usuarios; // Lista que conterá os dados a serem exibidos no corpo do JTable.
	private List<Cargo> cargos; // Lista que conterá os dados dos cargos associados a cada funcionário.
	
	public ModeloTabelaUsuario() { } // Construtor vazio.

	public ModeloTabelaUsuario(List<Usuario> usuarios, List<Cargo> cargos) { // Construtor.
		// Obtém um ArrayList de objetos Funcionario, contendo os dados dos funcionários cadastrados.
		this.usuarios = usuarios;
		this.cargos = cargos;
	}
	
	// Método da interface TableModel (implementação obrigatória). 
	// Retorna a quantidade de colunas do modelo da tabela.
	@Override
	public int getColumnCount() { 
		return colunas.length;
	}
	
    // Método da classe abstrata AbstractTableModel (implementação opcional). 
	// Retorna o nome da coluna recebida como argumento.
    // Sem este método, os nomes das colunas são exibidos no JTable como: A, B, C, D etc. 
	@Override
	public String getColumnName(int coluna) {
		return colunas[coluna];
	}

	// Método da interface TableModel (implementação obrigatória). 
	// Retorna a quantidade de linhas do modelo da tabela. 
	@Override
	public int getRowCount() {
		if (usuarios != null) // Se existir pelo menos um funcionário cadastrado.
			return usuarios.size();	
		return 0;
	}
	
	// Método da classe abstrata AbstractTableModel (implementação opcional). 
	// Retorna a classe da coluna recebida como argumento.
    // Sem este método, a coluna Plano de Saúde do JTable exibe valores true ou false, em vez de checkboxes.
	@Override
	public Class<?> getColumnClass(int coluna) {
		return getValueAt(0, coluna).getClass(); // A linha é zero, mas poderia ser qualquer número de linha,
		                                         // pois o que importa é a classe da coluna.
	}

	// Método da interface TableModel (implementação obrigatória).
	// Cada vez que é chamado, este método recupera o valor de um dos atributos de um funcionário do ArrayList.
	// Cada valor recuperado é então retornado para popular uma célula correspondente no JTable.
	@Override
    public Object getValueAt(int linha, int coluna) {
        Usuario usuario = usuarios.get(linha); // Recupera o objeto Funcionario presente na posição "linha" do ArrayList.
        Object valor = null;
		
        switch (coluna) { // Verifica qual atributo do funcionário será recuperado, com base na coluna recebida.
            case 0: // Coluna IdFuncionario
            	valor = usuario.getId(); 
            	break;
            case 1: // Coluna Login 
            	valor = usuario.getLogin(); 
            	break;
            case 2: // Coluna Senha 
            	valor = usuario.getSenha(); 
            	break;
            case 3: // Coluna CPF 
            	valor = usuario.getCpf(); 
            	break;
            case 4: // Coluna Nome 
            	valor = usuario.getNome(); 
            	break;
            case 5: // Coluna Telefone 
            	valor = usuario.getTelefone(); 
            	break;
            case 6: // Coluna Email 
            	valor = usuario.getEmail(); 
            	break;
            case 7: // Coluna Rua 
            	valor = usuario.getRua(); 
            	break;
            case 8: // Coluna Bairro 
            	valor = usuario.getBairro(); 
            	break;
            case 9: // Coluna Cidade 
            	valor = usuario.getCidade(); 
            	break;
            case 10: // Coluna CEP 
            	valor = usuario.getCep(); 
            	break;
            case 11: // Coluna Estado 
            	valor = usuario.getEstado(); 
            	break;
            case 12: // Coluna Cargo
				if (cargos != null) // Se existir pelo menos um cargo cadastrado.
					for (Cargo c : cargos)
						if (c.getId() == usuario.getCargo().getId())
							// Ao ser carregado, o JTable chama automaticamente o método toString dos objetos Cargo para convertê-los  
							// para String, pois o dado a ser exibido nele deve ser deste tipo. Como o método toString foi sobrescrito
							// na classe Cargo, de modo a retornar a descrição do cargo, é este o dado que será exibido no JTable.
							valor = c;
            	break;
        }
        return valor;
    }
	
	// Método da classe abstrata AbstractTableModel (implementação opcional).
	// Bloqueia para edição a célula recebida como argumento.
	@Override 
	public boolean isCellEditable(int linha, int coluna) { 
		return false;
	}
}