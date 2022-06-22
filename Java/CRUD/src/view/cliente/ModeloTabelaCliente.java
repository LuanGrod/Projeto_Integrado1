package view.cliente;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.cliente.Cliente;

@SuppressWarnings("serial")
public class ModeloTabelaCliente extends AbstractTableModel { // A classe abstrata AbstractTableModel implementa a interface TableModel.
	// Array de nomes a serem exibidos no cabeçalho do JTable.
	private String[] colunas = { "Código", "Nome", "CPF", "Telefone", "Email", "Rua", "Bairro", "Cidade", "CEP", "Estado"};
	private List<Cliente> clientes; // Lista que conterá os dados a serem exibidos no corpo do JTable.
	
	public ModeloTabelaCliente() { } // Construtor vazio.

	public ModeloTabelaCliente(List<Cliente> clientes) { // Construtor.
		// Obtém um ArrayList de objetos Funcionario, contendo os dados dos funcionários cadastrados.
		this.clientes = clientes;
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
		if (clientes != null) // Se existir pelo menos um funcionário cadastrado.
			return clientes.size();	
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
        Cliente cliente = clientes.get(linha); // Recupera o objeto Funcionario presente na posição "linha" do ArrayList.
        Object valor = null;
		
        switch (coluna) { // Verifica qual atributo do funcionário será recuperado, com base na coluna recebida.
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
	
	// Método da classe abstrata AbstractTableModel (implementação opcional).
	// Bloqueia para edição a célula recebida como argumento.
	@Override 
	public boolean isCellEditable(int linha, int coluna) { 
		return false;
	}
}