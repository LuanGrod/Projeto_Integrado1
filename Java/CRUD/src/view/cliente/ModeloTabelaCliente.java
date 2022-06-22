package view.cliente;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.cliente.Cliente;

@SuppressWarnings("serial")
public class ModeloTabelaCliente extends AbstractTableModel { // A classe abstrata AbstractTableModel implementa a interface TableModel.
	// Array de nomes a serem exibidos no cabe�alho do JTable.
	private String[] colunas = { "C�digo", "Nome", "CPF", "Telefone", "Email", "Rua", "Bairro", "Cidade", "CEP", "Estado"};
	private List<Cliente> clientes; // Lista que conter� os dados a serem exibidos no corpo do JTable.
	
	public ModeloTabelaCliente() { } // Construtor vazio.

	public ModeloTabelaCliente(List<Cliente> clientes) { // Construtor.
		// Obt�m um ArrayList de objetos Funcionario, contendo os dados dos funcion�rios cadastrados.
		this.clientes = clientes;
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
		if (clientes != null) // Se existir pelo menos um funcion�rio cadastrado.
			return clientes.size();	
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
        Cliente cliente = clientes.get(linha); // Recupera o objeto Funcionario presente na posi��o "linha" do ArrayList.
        Object valor = null;
		
        switch (coluna) { // Verifica qual atributo do funcion�rio ser� recuperado, com base na coluna recebida.
            case 0: // Coluna IdFuncionario
            	valor = cliente.getId(); 
            	break;
            case 1: // Coluna Nome 
            	valor = cliente.getNome(); 
            	break;
            case 2: // Coluna CPF
            	valor = cliente.getCpf(); 
            	break;
            case 3: // Coluna Telefone
            	valor = cliente.getTelefone(); 
            	break;
            case 4: // Coluna Email
            	valor = cliente.getEmail(); 
            	break;
            case 5: // Coluna Rua
            	valor = cliente.getEmail(); 
            	break;
            case 6: // Coluna Bairro
            	valor = cliente.getBairro(); 
            	break;
            case 7: // Coluna Cidade
            	valor = cliente.getCidade(); 
            	break;
            case 8: // Coluna CEP
            	valor = cliente.getCep(); 
            	break;
            case 9: // Coluna Estado
            	valor = cliente.getEstado(); 
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
}