package view.usuario;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.cargo.Cargo;
import model.usuario.Usuario;

@SuppressWarnings("serial")
public class ModeloTabelaUsuario extends AbstractTableModel { // A classe abstrata AbstractTableModel implementa a interface TableModel.
	// Array de nomes a serem exibidos no cabe�alho do JTable.
	private String[] colunas = { "C�digo", "Login", "Senha", "CPF", "Nome", "Telefone", "Email", "Rua", "Bairro", "Cidade", "CEP", "Estado", "Cargo"};
	private List<Usuario> usuarios; // Lista que conter� os dados a serem exibidos no corpo do JTable.
	private List<Cargo> cargos; // Lista que conter� os dados dos cargos associados a cada funcion�rio.
	
	public ModeloTabelaUsuario() { } // Construtor vazio.

	public ModeloTabelaUsuario(List<Usuario> usuarios, List<Cargo> cargos) { // Construtor.
		// Obt�m um ArrayList de objetos Funcionario, contendo os dados dos funcion�rios cadastrados.
		this.usuarios = usuarios;
		this.cargos = cargos;
	}
	
	// M�todo da interface TableModel (implementa��o obrigat�ria). 
	// Retorna a quantidade de colunas do modelo da tabela.
	@Override
	public int getColumnCount() { 
		return colunas.length;
	}
	
    // M�todo da classe abstrata AbstractTableModel (implementa��o opcional). 
	// Retorna o nome da coluna recebida como argumento.
    // Sem este m�todo, os nomes das colunas s�o exibidos no JTable como: A, B, C, D etc. 
	@Override
	public String getColumnName(int coluna) {
		return colunas[coluna];
	}

	// M�todo da interface TableModel (implementa��o obrigat�ria). 
	// Retorna a quantidade de linhas do modelo da tabela. 
	@Override
	public int getRowCount() {
		if (usuarios != null) // Se existir pelo menos um funcion�rio cadastrado.
			return usuarios.size();	
		return 0;
	}
	
	// M�todo da classe abstrata AbstractTableModel (implementa��o opcional). 
	// Retorna a classe da coluna recebida como argumento.
    // Sem este m�todo, a coluna Plano de Sa�de do JTable exibe valores true ou false, em vez de checkboxes.
	@Override
	public Class<?> getColumnClass(int coluna) {
		return getValueAt(0, coluna).getClass(); // A linha � zero, mas poderia ser qualquer n�mero de linha,
		                                         // pois o que importa � a classe da coluna.
	}

	// M�todo da interface TableModel (implementa��o obrigat�ria).
	// Cada vez que � chamado, este m�todo recupera o valor de um dos atributos de um funcion�rio do ArrayList.
	// Cada valor recuperado � ent�o retornado para popular uma c�lula correspondente no JTable.
	@Override
    public Object getValueAt(int linha, int coluna) {
        Usuario usuario = usuarios.get(linha); // Recupera o objeto Funcionario presente na posi��o "linha" do ArrayList.
        Object valor = null;
		
        switch (coluna) { // Verifica qual atributo do funcion�rio ser� recuperado, com base na coluna recebida.
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
							// Ao ser carregado, o JTable chama automaticamente o m�todo toString dos objetos Cargo para convert�-los  
							// para String, pois o dado a ser exibido nele deve ser deste tipo. Como o m�todo toString foi sobrescrito
							// na classe Cargo, de modo a retornar a descri��o do cargo, � este o dado que ser� exibido no JTable.
							valor = c;
            	break;
        }
        return valor;
    }
	
	// M�todo da classe abstrata AbstractTableModel (implementa��o opcional).
	// Bloqueia para edi��o a c�lula recebida como argumento.
	@Override 
	public boolean isCellEditable(int linha, int coluna) { 
		return false;
	}
	
	// M�todo da classe abstrata AbstractTableModel (implementa��o opcional).
	@Override
	// Este m�todo ser� usado para atualizar o JTable ap�s a altera��o de funcion�rios. 
	public void setValueAt(Object valor, int linha, int coluna) { 
		// O m�todo get da classe ArrayList recupera o objeto Funcionario existente na posi��o indicada pelo argumento "linha".
		switch (coluna) { 
		case 1: // Coluna Login 
			usuarios.get(linha).setLogin(valor.toString()); 
			break;
		case 2: // Coluna Senha
			usuarios.get(linha).setSenha(valor.toString()); 
			break;
		case 3: // Coluna Cpf
			usuarios.get(linha).setCpf(valor.toString()); 
			break;
		case 4: // Coluna Nome
			usuarios.get(linha).setNome(valor.toString()); 
			break;
		case 5: // Coluna Telefone
			usuarios.get(linha).setTelefone(valor.toString()); 
			break;
		case 6: // Coluna Email
			usuarios.get(linha).setEmail(valor.toString()); 
			break;
		case 7: // Coluna Rua
			usuarios.get(linha).setRua(valor.toString()); 
			break;
		case 8: // Coluna Bairro
			usuarios.get(linha).setBairro(valor.toString()); 
			break;
		case 9: // Coluna Cidade
			usuarios.get(linha).setCidade(valor.toString()); 
			break;
		case 10: // Coluna Cep
			usuarios.get(linha).setCep(valor.toString()); 
			break;
		case 11: // Coluna Estado
			usuarios.get(linha).setEstado(valor.toString()); 
			break;
		case 12: // Coluna Cargo
			usuarios.get(linha).setCargo((Cargo) valor); 
			break;
		}
		// M�todo da classe abstrata AbstractTableModel.
		// Informa todos os processadores de eventos que um valor do ArrayList "funcionarios"
		// foi alterado e que o valor da c�lula correspondente no JTable deve ser atualizado.
		fireTableCellUpdated(linha, coluna);
	}
	
	// Remove o funcion�rio exclu�do do ArrayList de funcion�rios e atualiza o JTable.
    public void removeUsuarioTabela(int linha) {
    	usuarios.remove(linha);
        
        // M�todo da classe abstrata AbstractTableModel.
		// Informa todos os processadores de eventos que um objeto do ArrayList "funcionarios"
		// foi exclu�do e que a linha correspondente no JTable deve ser removida.
        // Os argumentos s�o as linhas inicial e final. Elas s�o as mesmas, porque 
        // somente um funcion�rio poder� ser selecionado para exclus�o de cada vez.
        fireTableRowsDeleted(linha, linha);
    }
}