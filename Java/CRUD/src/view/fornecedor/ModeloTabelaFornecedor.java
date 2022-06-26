package view.fornecedor;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.fornecedor.Fornecedor;


@SuppressWarnings("serial")
public class ModeloTabelaFornecedor extends AbstractTableModel { // A classe abstrata AbstractTableModel implementa a interface TableModel.
	// Array de nomes a serem exibidos no cabe�alho do JTable.
	private String[] colunas = { "C�digo", "CNPJ", "Telefone", "Email", "Nome"};
	private List<Fornecedor> fornecedores; // Lista que conter� os dados a serem exibidos no corpo do JTable.
	
	public ModeloTabelaFornecedor() { } // Construtor vazio.

	public ModeloTabelaFornecedor(List<Fornecedor> fornecedores) { // Construtor.
		// Obt�m um ArrayList de objetos Funcionario, contendo os dados dos funcion�rios cadastrados.
		this.fornecedores = fornecedores;
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
		if (fornecedores != null) // Se existir pelo menos um funcion�rio cadastrado.
			return fornecedores.size();	
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
		Fornecedor fornecedor = fornecedores.get(linha); // Recupera o objeto Funcionario presente na posi��o "linha" do ArrayList.
        Object valor = null;
		
        switch (coluna) { // Verifica qual atributo do funcion�rio ser� recuperado, com base na coluna recebida.
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
			// M�todo da classe abstrata AbstractTableModel.
			// Informa todos os processadores de eventos que um valor do ArrayList "funcionarios"
			// foi alterado e que o valor da c�lula correspondente no JTable deve ser atualizado.
			fireTableCellUpdated(linha, coluna);
		}
		
		// Remove o funcion�rio exclu�do do ArrayList de funcion�rios e atualiza o JTable.
	    public void removeFornecedorTabela(int linha) {
	    	fornecedores.remove(linha);
	        
	        // M�todo da classe abstrata AbstractTableModel.
			// Informa todos os processadores de eventos que um objeto do ArrayList "funcionarios"
			// foi exclu�do e que a linha correspondente no JTable deve ser removida.
	        // Os argumentos s�o as linhas inicial e final. Elas s�o as mesmas, porque 
	        // somente um funcion�rio poder� ser selecionado para exclus�o de cada vez.
	        fireTableRowsDeleted(linha, linha);
	    }
}