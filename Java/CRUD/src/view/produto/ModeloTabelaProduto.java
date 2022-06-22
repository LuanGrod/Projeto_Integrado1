package view.produto;

import java.text.DecimalFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.categoria.Categoria;
import model.produto.Produto;


@SuppressWarnings("serial")
public class ModeloTabelaProduto extends AbstractTableModel { // A classe abstrata AbstractTableModel implementa a interface TableModel.
	// Array de nomes a serem exibidos no cabeçalho do JTable.
	private String[] colunas = { "Código", "Nome", "Preço Custo", "Preço Venda", "Qtd. estoque", "Categoria"};
	private List<Produto> produtos; // Lista que conterá os dados a serem exibidos no corpo do JTable.
	private List<Categoria> categorias; // Lista que conterá os dados dos cargos associados a cada funcionário.
	
	public ModeloTabelaProduto() { } // Construtor vazio.

	public ModeloTabelaProduto(List<Produto> produtos, List<Categoria> categorias) { // Construtor.
		// Obtém um ArrayList de objetos Funcionario, contendo os dados dos funcionários cadastrados.
		this.produtos = produtos;
		this.categorias = categorias;
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
		if (produtos != null) // Se existir pelo menos um funcionário cadastrado.
			return produtos.size();	
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
        Produto produto = produtos.get(linha); // Recupera o objeto Funcionario presente na posição "linha" do ArrayList.
        Object valor = null;
        DecimalFormat df = new DecimalFormat("##,##0.00");
		
		
        switch (coluna) { // Verifica qual atributo do funcionário será recuperado, com base na coluna recebida.
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