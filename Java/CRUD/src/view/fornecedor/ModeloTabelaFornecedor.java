package view.fornecedor;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.fornecedor.Fornecedor;


@SuppressWarnings("serial")
public class ModeloTabelaFornecedor extends AbstractTableModel { // A classe abstrata AbstractTableModel implementa a interface TableModel.
	// Array de nomes a serem exibidos no cabeçalho do JTable.
	private String[] colunas = { "Código", "CNPJ", "Telefone", "Email", "Nome"};
	private List<Fornecedor> fornecedores; // Lista que conterá os dados a serem exibidos no corpo do JTable.
	
	public ModeloTabelaFornecedor() { } // Construtor vazio.

	public ModeloTabelaFornecedor(List<Fornecedor> fornecedores) { // Construtor.
		// Obtém um ArrayList de objetos Funcionario, contendo os dados dos funcionários cadastrados.
		this.fornecedores = fornecedores;
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
		if (fornecedores != null) // Se existir pelo menos um funcionário cadastrado.
			return fornecedores.size();	
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
		Fornecedor fornecedor = fornecedores.get(linha); // Recupera o objeto Funcionario presente na posição "linha" do ArrayList.
        Object valor = null;
		
        switch (coluna) { // Verifica qual atributo do funcionário será recuperado, com base na coluna recebida.
            case 0: // Coluna idFornecedor
            	valor = fornecedor.getId(); 
            	break;
            case 1: // Coluna CNPJ 
            	valor = fornecedor.getCnpj(); 
            	break;
            case 2: // Coluna Telefone
            	valor = fornecedor.getTelefone(); 
            	break;
            case 3: // Coluna Email
            	valor = fornecedor.getEmail(); 
            	break;
            case 4: // Coluna Nome
            	valor = fornecedor.getNome(); 
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
	
	// Método da classe abstrata AbstractTableModel (implementação opcional).
		@Override
		// Este método será usado para atualizar o JTable após a alteração de funcionários. 
		public void setValueAt(Object valor, int linha, int coluna) { 
			// O método get da classe ArrayList recupera o objeto Funcionario existente na posição indicada pelo argumento "linha".
			switch (coluna) { 
			case 1: // Coluna cnpj 
				fornecedores.get(linha).setCnpj(valor.toString()); 
				break;
			case 2: // Coluna telefone 
				fornecedores.get(linha).setTelefone(valor.toString()); 
				break;
			case 3: // Coluna email 
				fornecedores.get(linha).setEmail(valor.toString()); 
				break;
			case 4: // Coluna nome 
				fornecedores.get(linha).setNome(valor.toString()); 
				break;
			}
			// Método da classe abstrata AbstractTableModel.
			// Informa todos os processadores de eventos que um valor do ArrayList "funcionarios"
			// foi alterado e que o valor da célula correspondente no JTable deve ser atualizado.
			fireTableCellUpdated(linha, coluna);
		}
		
		// Remove o funcionário excluído do ArrayList de funcionários e atualiza o JTable.
	    public void removeFornecedorTabela(int linha) {
	    	fornecedores.remove(linha);
	        
	        // Método da classe abstrata AbstractTableModel.
			// Informa todos os processadores de eventos que um objeto do ArrayList "funcionarios"
			// foi excluído e que a linha correspondente no JTable deve ser removida.
	        // Os argumentos são as linhas inicial e final. Elas são as mesmas, porque 
	        // somente um funcionário poderá ser selecionado para exclusão de cada vez.
	        fireTableRowsDeleted(linha, linha);
	    }
}