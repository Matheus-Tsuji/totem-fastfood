package Totem;

public class Produto {
	
	// Variaveis
	public String nomeProduto;
	public double precoProduto;
	String categoriaProduto;
	int desconto;
	boolean possui_combo;
	/**
	 * @param nomeProduto
	 * @param precoProduto
	 * @param desconto
	 * @param possui_combo
	 */
	
	// Construtor
	public Produto(String nome_produto, double preco_produto, String categoriaProduto) {
		super();
		this.nomeProduto = nome_produto;
		this.precoProduto = preco_produto;
		this.categoriaProduto = categoriaProduto;
		
		// Valores iniciais já declarados
		this.desconto = 0;
		this.possui_combo = false;
	}
	
	// Métodos
	public double inserirDesconto(int quantidade_desonto) {
	    double percentual = (double)quantidade_desonto / 100.0;
	    double valorDesconto = precoProduto * percentual;

	    double precoAtual = precoProduto - valorDesconto;

	    return precoAtual;
	}
	
}
