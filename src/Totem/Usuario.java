package Totem;

import java.util.Scanner;

public class Usuario {
	
	// Variáveis
	public String nomeUsuario;
	public String emailUsuario;
	public String senhaUsuario;
	int idadeUsuario;
	String cpf_usuario;
	
	/**
	 * @param nomeUsuario
	 * @param emailUsuario
	 * @param senhaUsuario
	 * @param idadeUsuario
	 * @param cpf_usuario
	 */
	
	// Contrutor
	public Usuario(String nomeUsuario, String emailUsuario, String senhaUsuario, int idadeUsuario, String cpf_usuario) {
		super();
		this.nomeUsuario = nomeUsuario;
		this.emailUsuario = emailUsuario;
		this.senhaUsuario = senhaUsuario;
		this.idadeUsuario = idadeUsuario;
		this.cpf_usuario = cpf_usuario;
	}
	
	// Métodos
	public void informacoes() {
		System.out.println("\nInformações do Usuário:");
		System.out.println("Nome: " + nomeUsuario 
							+ "\nEmail: " + emailUsuario
							+ "\nCPF: " + cpf_usuario);
	}
	
	public static String lerNome(Scanner scanner) {
		String nome;
		do {
			System.out.print("\n" + Totem.AMARELO_TEXT + "- Nome Completo: " + Totem.RESET);
			nome = scanner.nextLine();
			if(nome.isEmpty())
				System.out.println(Totem.ERRO + "\nNome vazio. Não pode estar vazio; tente novamente." + Totem.RESET);
		} while (nome.isEmpty());
		return nome;
	}

	public static String lerEmail(Scanner scanner) {
		String email;
		do {
			System.out.print(Totem.AMARELO_TEXT + "- Email: " + Totem.RESET);
			email = scanner.nextLine().trim().toLowerCase();
			if(!email.contains("@") || email.isEmpty())
				System.out.println(Totem.ERRO + "\nEmail inválido. Verifique se não está vazio ou está completo (contém o '@')." + Totem.RESET);
		} while (!email.contains("@") || email.isEmpty());
		return email;
	}
	
	public static String lerSenha(Scanner scanner) {
		String senha;
		do {
			System.out.print(Totem.AMARELO_TEXT + "- Senha: " + Totem.RESET);
			senha = scanner.nextLine().trim();
			if(senha.length() < 8)
				System.out.println(Totem.ERRO + "A senha deve conter um mínimo de 8 caracteres." + Totem.RESET);
		} while (senha.length() < 8);
		return senha;
	}
	
	public static String lerSenhaLogin(Scanner scanner) {
		String senha;
		do {
			System.out.print(Totem.AMARELO_TEXT + "- Senha: " + Totem.RESET);
			senha = scanner.nextLine().trim();
			if(senha.isEmpty())
				System.out.println(Totem.ERRO + "A senha não pode ser vazia." + Totem.RESET);
		} while (senha.isEmpty());
		return senha;
	}
	
	public static int lerIdade(Scanner scanner) {
		int idade = 0;
		boolean entradaValida = false;
		
		do {
			try {
				System.out.print(Totem.AMARELO_TEXT + "- Idade: " + Totem.RESET);
				
				if (scanner.hasNextInt()) {
					idade = scanner.nextInt();
					
					if(idade <= 0 || idade > 120) {
						System.out.println(Totem.ERRO +"\nIdade inválida. Deve ser maior que 0 e menor que 120." + Totem.RESET);
						entradaValida = false;
					} else {
						entradaValida = true;
					}
				} else {
					System.out.println(Totem.ERRO + "\nErro: Por favor, digite um número inteiro para a idade." + Totem.RESET);
                    scanner.next();
                    entradaValida = false;
				}
				
			} catch (Exception e) {
				System.out.println("Erro: " + e);
			} finally {
				scanner.nextLine();
			}
		} while (!entradaValida);
		
		return idade;
	}
	
	public static String lerCpf(Scanner scanner) {
	    String cpf = "";
	    boolean entradaValida = false;
	    
	    do {
	        System.out.print(Totem.AMARELO_TEXT + "- CPF (11 dígitos, apenas números): " + Totem.RESET);
	        cpf = scanner.nextLine().trim();
	        
	        if (cpf.length() != 11) {
	            System.out.println(Totem.ERRO + "\nCPF inválido. Deve conter exatamente 11 dígitos." + Totem.RESET);
	            entradaValida = false;
	            
	        } else if (!cpf.matches("\\d+")) { // REGEX
	            System.out.println(Totem.ERRO + "\nCPF inválido. O campo deve conter apenas números." + Totem.RESET);
	            entradaValida = false;
	            
	        } else {
	            entradaValida = true;
	        }
	        
	    } while (!entradaValida);
	    
	    return cpf;
	}
	
}
