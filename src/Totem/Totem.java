package Totem;

import java.io.IOException;
import java.util.Scanner;

/*
 * 
 * Totem Fast Food no console.
 * 
 * O que deve ter: 
 * Usuario,
 * Carrinho,
 * Valor, 
 * Produtos -> com categorias
 * Pedido -> codigo do pedido
 * Lista de Produtos
 * 
 * Interação com usuário
 * Opção de pagamento
 * 
 * 
 * */


// C:/users/usuário/eclipse-workspace/Totem/src/totem/

public class Totem {
	
	// Variaveis de cor do console
	public static final String RESET = "\u001B[0m";
	public static final String VERMELHO_TEXT = "\u001B[31m";
    public static final String VERDE_TEXT = "\u001B[32m";
    public static final String AZUL_TEXT = "\u001B[34m";
    public static final String AMARELO_TEXT = "\u001B[33m";
    public static final String CIANO_TEXT = "\u001B[36m";
    
    public static final String ROXO_BG = "\u001b[45m";
    
    public static final String NEGRITO = "\u001B[1m";
    public static final String SUBLINHADO = "\u001B[4m";
    
    public static final String codigoMaster = "\u001B[33;41m";
    public static final String ERRO = "\u001B[1;31m";
	
	public static Usuario registraUsuario(Scanner scanner) {
		String nomeUsuario;
		String emailUsuario;
		String senhaUsuario;
		String confirmarSenha;
		int idadeUsuario;
		String cpf_usuario;
		
		limparConsole();
		System.out.println("\n░█▀▀░█▀█░█▀▄░█▀█░█▀▀░▀█▀░█▀▄░█▀█░░░█▀▄░█▀▀░░░█░█░█▀▀░█░█░█▀█░█▀▄░▀█▀░█▀█\r\n"
				           + "░█░░░█▀█░█░█░█▀█░▀▀█░░█░░█▀▄░█░█░░░█░█░█▀▀░░░█░█░▀▀█░█░█░█▀█░█▀▄░░█░░█░█\r\n"
				           + "░▀▀▀░▀░▀░▀▀░░▀░▀░▀▀▀░░▀░░▀░▀░▀▀▀░░░▀▀░░▀▀▀░░░▀▀▀░▀▀▀░▀▀▀░▀░▀░▀░▀░▀▀▀░▀▀▀");
		
		nomeUsuario 	= Usuario.lerNome(scanner);
		emailUsuario 	= Usuario.lerEmail(scanner);
		senhaUsuario 	= Usuario.lerSenha(scanner);

		do {
			System.out.print("Confirmar senha: ");
			confirmarSenha = scanner.nextLine();
			if(!confirmarSenha.equals(senhaUsuario))
				System.out.println("\nSenha incorreta. Tente novamente."); 
		} while (!confirmarSenha.equals(senhaUsuario));
		
		idadeUsuario = Usuario.lerIdade(scanner);
		cpf_usuario = Usuario.lerCpf(scanner);
		
		if(BancoDeDados.verificarUsuario(cpf_usuario, emailUsuario)) {
			System.out.println(AMARELO_TEXT + "Usuário já cadastrado. O " + NEGRITO + " CPF " + "ou" + NEGRITO + " E-mail " + "informado já existe no sistema. Tente fazer login." + RESET);
			fazerLogin(scanner);
			return null;
		}
		
		Usuario usuario = new Usuario(nomeUsuario, emailUsuario, senhaUsuario, idadeUsuario, cpf_usuario);
		System.out.println("\nUsuário registrado.");
		
		BancoDeDados.inserirUsuario(usuario);
		
		entrar(scanner);
		
		return usuario;
	}
	
	public static Usuario fazerLogin(Scanner scanner) {
		limparConsole();
		System.out.println("\n░█░░░█▀█░█▀▀░▀█▀░█▀█\r\n"
						   + "░█░░░█░█░█░█░░█░░█░█\r\n"
						   + "░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀░▀\n");
	    String email = Usuario.lerEmail(scanner);
	    String senha = Usuario.lerSenhaLogin(scanner);

	    Usuario usuario = BancoDeDados.autenticarUsuario(email, senha);

	    if (usuario != null) {
	    	System.out.println(VERDE_TEXT + "\nSucesso! (^v^)");
	    	animacaoConsole(4);
	    	limparConsole();
	        System.out.println("\nBem-vindo(a), " + NEGRITO + usuario.nomeUsuario + "!" + RESET);
	    } else {
	        System.out.println(ERRO + "\nAcesso negado: E-mail ou senha inválidos." + RESET);
	    }
	    
	    return usuario;
	}

	public static void inicio(Scanner scanner) {
		int opcao = 0;
		boolean entradaValida = false;
		
		do {
			limparConsole();
			System.out.println("\n" + codigoMaster
				           + "██████╗ ███████╗███╗   ███╗              ██╗   ██╗██╗███╗   ██╗██████╗  ██████╗  ██╗ █████╗ ██╗ ██╗\r\n"
						   + "██╔══██╗██╔════╝████╗ ████║              ██║   ██║██║████╗  ██║██╔══██╗██╔═══██╗██╔╝██╔══██╗╚██╗██║\r\n"
						   + "██████╔╝█████╗  ██╔████╔██║    █████╗    ██║   ██║██║██╔██╗ ██║██║  ██║██║   ██║██║ ███████║ ██║██║\r\n"
						   + "██╔══██╗██╔══╝  ██║╚██╔╝██║    ╚════╝    ╚██╗ ██╔╝██║██║╚██╗██║██║  ██║██║   ██║██║ ██╔══██║ ██║╚═╝\r\n"
						   + "██████╔╝███████╗██║ ╚═╝ ██║               ╚████╔╝ ██║██║ ╚████║██████╔╝╚██████╔╝╚██╗██║  ██║██╔╝██╗\r\n"
						   + "╚═════╝ ╚══════╝╚═╝     ╚═╝                ╚═══╝  ╚═╝╚═╝  ╚═══╝╚═════╝  ╚═════╝  ╚═╝╚═╝  ╚═╝╚═╝ ╚═╝\r\n"
						   + "\r" + RESET);
			
			System.out.print("\n░█▀▀░█▀▀░█▀▀░█▀█░█░░░█░█░█▀█░░░█░█░█▄█░█▀█░░░█▀█░█▀█░█▀▀░█▀█░█▀█░░░░\r\n"
							 + "░█▀▀░▀▀█░█░░░█░█░█░░░█▀█░█▀█░░░█░█░█░█░█▀█░░░█░█░█▀▀░█░░░█▀█░█░█░░▀░\r\n"
							 + "░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀░▀░▀░▀░░░▀▀▀░▀░▀░▀░▀░░░▀▀▀░▀░░░▀▀▀░▀░▀░▀▀▀░░▀░\n"
								+ AMARELO_TEXT + "\n1-" + RESET + " Comer aqui." 
								+ AMARELO_TEXT + "\n2-" + RESET +" Levar para casa. \n" 
								+ AMARELO_TEXT + "\nDigite a opção desejada: " + RESET);
			
			if(scanner.hasNextInt()) {
				opcao = scanner.nextInt();
				
				System.out.println("");
				scanner.nextLine();
				
				switch (opcao) {
					case 1:
						animacaoConsole(2);
						entrar(scanner);
						entradaValida = true;
						break;
					case 2:
						animacaoConsole(2);
						entrar(scanner);
						entradaValida = true;
						break;
					default:
						System.out.println(ERRO + "\nOpção invalida. Tente novamente...\n" + RESET);
						entradaValida = false;
				}
			} else {
				System.out.println(ERRO + "\n\nOpção deve conter um número.\n" + RESET);
				entradaValida = false;
			}
			
		} while (!entradaValida);
	}


	public static void entrar(Scanner scanner) {
		int opcao = 0;
		boolean entradaValida = false;
		
		do {
			limparConsole();
			System.out.print("\n░█▀▀░█▀█░▀█▀░█▀▄░█▀█░█▀▄\r\n"
					         + "░█▀▀░█░█░░█░░█▀▄░█▀█░█▀▄\r\n"
					         + "░▀▀▀░▀░▀░░▀░░▀░▀░▀░▀░▀░▀\n"
							+ AMARELO_TEXT + "\n1- " + RESET + "Login"
							+ AMARELO_TEXT + "\n2- " + RESET + "Cadastrar"
							+ AMARELO_TEXT + "\n3- " + RESET + "Entrar sem cadastro. \n"
							+ AMARELO_TEXT + "\nDigite a opção desejada: " + RESET);
			
			if(scanner.hasNextInt()) {
				opcao = scanner.nextInt();
				
				System.out.println("");
				scanner.nextLine();
				
				switch (opcao) {
					case 1:
						animacaoConsole(3);
						fazerLogin(scanner);
						entradaValida = true;
						break;
					case 2:
						animacaoConsole(3.5);
						registraUsuario(scanner);
						entradaValida = true;
						break;
					case 3:
						System.out.printf("Opção %d referente à entrar sem cadastro", opcao);
						entradaValida = true;
						break;
					default:
						System.out.println(ERRO + "\nOpção invalida. Tente novamente...\n" + RESET);
						entradaValida = false;
				}
				
			} else {
				System.out.println(ERRO + "\n\nOpção deve conter um número.\n" + RESET);
				entradaValida = false;
			}
			
		} while (!entradaValida);
	}
	
	public static void totemInit(Scanner scanner) {
		try {
			limparConsole();
			System.out.println(codigoMaster + "                                                     \r\n"
											+ "   ████████╗ ██████╗ ████████╗███████╗███╗   ███╗    \r\n"
											+ "   ╚══██╔══╝██╔═══██╗╚══██╔══╝██╔════╝████╗ ████║    \r\n"
											+ "      ██║   ██║   ██║   ██║   █████╗  ██╔████╔██║    \r\n"
											+ "      ██║   ██║   ██║   ██║   ██╔══╝  ██║╚██╔╝██║    \r\n"
											+ "      ██║   ╚██████╔╝   ██║   ███████╗██║ ╚═╝ ██║    \r\n"
											+ "      ╚═╝    ╚═════╝    ╚═╝   ╚══════╝╚═╝     ╚═╝    \r\n"
											+ "                                                     " + RESET);

			System.out.println(AMARELO_TEXT
					+ "\r\n"
					+ "                                                                    \r\n"
					+ "                     %%%%%%%%%%%                                    \r\n"
					+ "              %%%:---=-.----=--=----%%%                             \r\n"
					+ "          %%%-:-=----------------=-====-:%%                         \r\n"
					+ "        %%----------------.=-----====.-===--%                       \r\n"
					+ "      %%----=------.------------:---=--======:%                     \r\n"
					+ "     %-----=----------------------=-======.====%                    \r\n"
					+ "    %:--------------------.:-------=============%                   \r\n"
					+ "   @%-----------=----------------=====-==========%                  \r\n"
					+ "   %:----------------------------================%                  \r\n"
					+ "  %%%%--------------------=---====-=============%%%%                \r\n"
					+ " %---++%%%%------------========------------@%%%+++++%               \r\n"
					+ " @%%=%-=====+++=%%%%%%%%%%%%%%%%%%%%%%+*+*+++++%#@%%                \r\n"
					+ "    %%----:%%-===----====+-===++++===++==@=====%#@                  \r\n"
					+ "    %***#####%%%%%##%%--%%%##%%=:%@#%%%%@#######%                   \r\n"
					+ "   %-=%%*****#*********#####%###********#####*%%==%                 \r\n"
					+ "  %--------+%%%%%%%%%%%%%======*%%%%%%%%%%-=======-%                \r\n"
					+ "  %%%%%#%%%%%%%%---:::::::::::::----===%%%%%%%#%%%%                 \r\n"
					+ "    %##****#*****#*%%%--..:::::::--%%####*#######%                  \r\n"
					+ "   %=%#####***#******##%%%--.:--%%#######*#####%%%                  \r\n"
					+ "   %---=@%%%########***####%%%%############%%%==+@#                 \r\n"
					+ "   @@--------===%%%%%%%%%%%%%%%%%%%%%%%=====+====%                  \r\n"
					+ "     %------------=----------------=============%                   \r\n"
					+ "       %%==----------=======================%%%                     \r\n"
					+ "           @%%%%%====================*%%%%@                         \r\n"
					+ "                          ##*                                       \r\n"
					+ "                                                                    \r\n"
					+ "\n"
					+  RESET);

			animacaoConsole(4);
			Thread.sleep(2000);
			inicio(scanner);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		limparConsole();
		
		BancoDeDados.criarTabelaUsuario();  
		BancoDeDados.criarTabelaProduto();
		
		totemInit(scanner);
		
//		Produto carne = new Produto("Picanha", 145.76);
//		
//		System.out.println(carne.nomeProduto + " - " + carne.precoProduto);
//		System.out.printf("Preço com desconto: R$%.2f", carne.inserirDesconto(20));
		
		scanner.close();
	}
	
	static void animacaoConsole(double segundos_animacao) {
	    try {
	        long tempoFinal = System.currentTimeMillis() + (long)(segundos_animacao * 1000);
	        char[] spinner = {'|', '/', '-', '\\'};
	        int i = 0;

	        while (System.currentTimeMillis() < tempoFinal) {
	            System.out.print(CIANO_TEXT + "\rProcessando... " + spinner[i % spinner.length] + RESET);
	            Thread.sleep(75);
	            i++;
	        }
	        
	        System.out.print(VERDE_TEXT + "\r\nProcessamento concluído! (^v^)\n" + RESET);

	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void limparConsole() {
		try {
			String os = System.getProperty("os.name");
			Thread.sleep(1250);
			if(os.contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
		} catch (IOException | InterruptedException e) {
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		}

	}

}
