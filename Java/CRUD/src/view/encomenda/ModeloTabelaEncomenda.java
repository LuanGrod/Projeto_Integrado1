package view.encomenda;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.encomenda.Encomenda;
import model.fornecedor.Fornecedor;
import model.produto.Produto;


@SuppressWarnings("serial")
public class ModeloTabelaEncomenda extends AbstractTableModel { // A classe abstrata AbstractTableModel implementa a interface TableModel.
	// Array de nomes a serem exibidos no cabeçalho do JTable.
	private String[] colunas = { "Código", "Produto", "Fornecedor", "Quantidade", "Situação"};
	private List<Encomenda> encomendas; // Lista que conterá os dados a serem exibidos no corpo do JTable.
	private List<Produto> produtos; // Lista que conterá os dados dos cargos associados a cada funcionário.
	private List<Fornecedor> fornecedores; // Lista que conterá os dados dos cargos associados a cada funcionário.
	
	public ModeloTabelaEncomenda() { } // Construtor vazio.

	public ModeloTabelaEncomenda(List<Encomenda> encomendas, List<Produto> produtos, List<Fornecedor> fornecedores) { // Construtor.
		// Obtém um ArrayList de objetos Funcionario, contendo os dados dos funcionários cadastrados.
		this.encomendas = encomendas;
		this.produtos = produtos;
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
		if (encomendas != null) // Se existir pelo menos um funcionário cadastrado.
			return encomendas.size();	
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
        Encomenda encomenda = encomendas.get(linha); // Recupera o objeto Funcionario presente na posição "linha" do ArrayList.
        Object valor = null;
		
        switch (coluna) { // Verifica qual atributo do funcionário será recuperado, com base na coluna recebida.
            case 0: // Coluna idencomenda
            	valor = encomenda.getId(); 
            	break;
            case 1: // Coluna produto
				if (produtos != null) // Se existir pelo menos um cargo cadastrado.
					for (Produto p : produtos)
						if (p.getId() == encomenda.getProduto().getId())
							// Ao ser carregado, o JTable chama automaticamente o método toString dos objetos Cargo para convertê-los  
							// para String, pois o dado a ser exibido nele deve ser deste tipo. Como o método toString foi sobrescrito
							// na classe Cargo, de modo a retornar a descrição do cargo, é este o dado que será exibido no JTable.
							valor = p;
            	break;
            case 2: // Coluna fornecedor
				if (fornecedores != null) // Se existir pelo menos um cargo cadastrado.
					for (Fornecedor f : fornecedores)
						if (f.getId() == encomenda.getFornecedor().getId())
							// Ao ser carregado, o JTable chama automaticamente o método toString dos objetos Cargo para convertê-los  
							// para String, pois o dado a ser exibido nele deve ser deste tipo. Como o método toString foi sobrescrito
							// na classe Cargo, de modo a retornar a descrição do cargo, é este o dado que será exibido no JTable.
							valor = f;
            	break;
            case 3: // Coluna quantidade
            	valor = encomenda.getQuantidade(); 
            	break;
            case 4: // Coluna situacao
            	valor = encomenda.getSituacao(); 
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
		case 1: // Coluna produto
			encomendas.get(linha).setProduto((Produto) valor); 
			break;
		case 2: // Coluna fornecedor
			encomendas.get(linha).setFornecedor((Fornecedor) valor); 
			break;
		case 3:// Coluna quantidade 
			encomendas.get(linha).setQuantidade(Integer.parseInt(valor.toString())); 
			break;
		case 4:// Coluna situacao 
			encomendas.get(linha).setSituacao(valor.toString()); 
			break;
		}
		// Método da classe abstrata AbstractTableModel.
		// Informa todos os processadores de eventos que um valor do ArrayList "funcionarios"
		// foi alterado e que o valor da célula correspondente no JTable deve ser atualizado.
		fireTableCellUpdated(linha, coluna);
	}
	
	// Remove o funcionário excluído do ArrayList de funcionários e atualiza o JTable.
    public void removeEncomendaTabela(int linha) {
    	encomendas.remove(linha);
        
        // Método da classe abstrata AbstractTableModel.
		// Informa todos os processadores de eventos que um objeto do ArrayList "funcionarios"
		// foi excluído e que a linha correspondente no JTable deve ser removida.
        // Os argumentos são as linhas inicial e final. Elas são as mesmas, porque 
        // somente um funcionário poderá ser selecionado para exclusão de cada vez.
        fireTableRowsDeleted(linha, linha);
    }
}