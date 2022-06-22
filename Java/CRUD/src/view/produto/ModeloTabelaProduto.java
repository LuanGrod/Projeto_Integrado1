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
}