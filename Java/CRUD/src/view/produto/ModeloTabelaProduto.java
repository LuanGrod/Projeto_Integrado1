package view.produto;

import java.text.DecimalFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.categoria.Categoria;
import model.produto.Produto;


@SuppressWarnings("serial")
public class ModeloTabelaProduto extends AbstractTableModel { // A classe abstrata AbstractTableModel implementa a interface TableModel.
	// Array de nomes a serem exibidos no cabe�alho do JTable.
	private String[] colunas = { "C�digo", "Nome", "Pre�o Custo", "Pre�o Venda", "Qtd. estoque", "Categoria"};
	private List<Produto> produtos; // Lista que conter� os dados a serem exibidos no corpo do JTable.
	private List<Categoria> categorias; // Lista que conter� os dados dos cargos associados a cada funcion�rio.
	
	public ModeloTabelaProduto() { } // Construtor vazio.

	public ModeloTabelaProduto(List<Produto> produtos, List<Categoria> categorias) { // Construtor.
		// Obt�m um ArrayList de objetos Funcionario, contendo os dados dos funcion�rios cadastrados.
		this.produtos = produtos;
		this.categorias = categorias;
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
		if (produtos != null) // Se existir pelo menos um funcion�rio cadastrado.
			return produtos.size();	
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
        Produto produto = produtos.get(linha); // Recupera o objeto Funcionario presente na posi��o "linha" do ArrayList.
        Object valor = null;
        DecimalFormat df = new DecimalFormat("##,##0.00");
		
		
        switch (coluna) { // Verifica qual atributo do funcion�rio ser� recuperado, com base na coluna recebida.
            case 0: // Coluna id
            	valor = produto.getId(); 
            	break;
            case 1: // Coluna nome 
            	valor = produto.getNome(); 
            	break;
            case 2: // Coluna preco custo 
            	valor = df.format(produto.getPrecoCusto());
            	break;
            case 3: // Coluna preco venda 
            	valor = df.format(produto.getPrecoVenda());
            	break;
            case 4: // Coluna qtd estoque 
            	valor = produto.getQtdEstoque(); 
            	break;
            case 5: // Coluna categoria
				if (categorias != null) // Se existir pelo menos um cargo cadastrado.
					for (Categoria c : categorias)
						if (c.getId() == produto.getCategoria().getId())
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
            case 1: // Coluna nome 
            	produtos.get(linha).setNome(valor.toString()); 
            	break;
            case 2: // Coluna preco custo 
            	produtos.get(linha).setPrecoCusto(Double.parseDouble(valor.toString())); 
            	break;
            case 3: // Coluna preco venda 
            	produtos.get(linha).setPrecoVenda(Double.parseDouble(valor.toString())); 
            	break;
            case 4: // Coluna qtd estoque 
            	produtos.get(linha).setQtdEstoque(Integer.parseInt(valor.toString())); 
            	break;
            case 5: // Coluna categoria
    			produtos.get(linha).setCategoria((Categoria) valor); 
            	break;
			}
			// M�todo da classe abstrata AbstractTableModel.
			// Informa todos os processadores de eventos que um valor do ArrayList "funcionarios"
			// foi alterado e que o valor da c�lula correspondente no JTable deve ser atualizado.
			fireTableCellUpdated(linha, coluna);
		}
		
		// Remove o funcion�rio exclu�do do ArrayList de funcion�rios e atualiza o JTable.
	    public void removeProdutoTabela(int linha) {
	    	produtos.remove(linha);
	        
	        // M�todo da classe abstrata AbstractTableModel.
			// Informa todos os processadores de eventos que um objeto do ArrayList "funcionarios"
			// foi exclu�do e que a linha correspondente no JTable deve ser removida.
	        // Os argumentos s�o as linhas inicial e final. Elas s�o as mesmas, porque 
	        // somente um funcion�rio poder� ser selecionado para exclus�o de cada vez.
	        fireTableRowsDeleted(linha, linha);
	    }
}