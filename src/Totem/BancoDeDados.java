package Totem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BancoDeDados {
	
	// código para inserir no terminal na pasta lib: java -jar h2-2.3.232.jar
	
	private static final String JDBC_URL = "jdbc:h2:file:C:/Users/Usuário/eclipse-workspace/Totem/data/totem_db_final;AUTO_SERVER=TRUE";
    private static final String USER = "admin"; 
    private static final String PASSWORD = "1234";
    
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }
    
    public static void criarTabelaUsuario() {
    	try (Connection conn = getConnection(); Statement stat = conn.createStatement()) {
    		
			String sql = "CREATE TABLE IF NOT EXISTS USUARIO (" +
							"id_usuario INT PRIMARY KEY AUTO_INCREMENT, " +
			                "cpf VARCHAR(11) NOT NULL UNIQUE, " + 
		                    "nome VARCHAR(255) NOT NULL, " +
		                    "email VARCHAR(255) NOT NULL UNIQUE, " +
		                    "senha VARCHAR(255) NOT NULL, " +
		                    "idade INT)";
			
			stat.execute(sql);
			System.out.println(Totem.VERDE_TEXT + "Tabela USUARIO verificada/criada" + Totem.RESET);
			
		} catch (SQLException e) {
			System.out.println(Totem.ERRO + "Erro ao criar a tabela USUARIO: " + e.getMessage() + Totem.RESET);
		}
    }
    
    public static void criarTabelaProduto() {
    	try (Connection conn = getConnection(); Statement stat = conn.createStatement()) {
    		
			String sql = "CREATE TABLE IF NOT EXISTS PRODUTOS (" +
		                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
		                    "categoria VARCHAR(255) NOT NULL," +
		                    "nome VARCHAR(255) NOT NULL, " +
		                    "preco DOUBLE NOT NULL)";
			
			stat.execute(sql);
			System.out.println(Totem.VERDE_TEXT + "\nTabela PRODUTOS verificada/criada" + Totem.RESET);
			
		} catch (SQLException e) {
			System.out.println(Totem.ERRO + "\nErro ao criar a tabela PRODUTOS: " + e.getMessage() + Totem.RESET);
		}
    }
    
    public static void inserirUsuario(Usuario usuario) {
    	String sql = "INSERT INTO USUARIO (cpf, nome, email, senha, idade) VALUES (?, ?, ?, ?, ?)";
    	
    	try (Connection conn = getConnection(); PreparedStatement pstat = conn.prepareStatement(sql)) {
    		
			pstat.setString(1, usuario.cpf_usuario);
			pstat.setString(2, usuario.nomeUsuario);
			pstat.setString(3, usuario.emailUsuario);
			pstat.setString(4, usuario.senhaUsuario);
            pstat.setInt(5, usuario.idadeUsuario);
            
            int linhasAfetadas = pstat.executeUpdate();
            if (linhasAfetadas > 0) {
            	System.out.println("\nUsuário " + usuario.nomeUsuario + " registrado no BD com sucesso.");
            }
			
		} catch (SQLException e) {
			System.out.println("Erro ao registrar usuário no BD: " + e.getMessage());
		}
    }
    
    public static void inserirProduto(Produto produto) {
    	String sql = "INSERT INTO PRODUTOS (nome, preco) VALUES (?,?)";
    	
    	try (Connection conn = getConnection(); PreparedStatement pstat = conn.prepareStatement(sql)) {
			
    		pstat.setString(1, produto.categoriaProduto);
    		pstat.setString(2, produto.nomeProduto);
    		pstat.setDouble(3, produto.precoProduto);
    		
    		int linhasAfetadas = pstat.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Produto '" + produto.nomeProduto + "' inserido com sucesso.");
            }
    		
		} catch (SQLException e) {
			System.out.println("Erro ao registrar usuário no BD: " + e.getMessage());
		}
    }
    
    public static boolean verificarUsuario(String cpf, String email) {
    	String sql = "SELECT COUNT(cpf) FROM USUARIO WHERE cpf = ? OR email = ?";
    	
    	try (Connection conn = getConnection(); PreparedStatement pstat = conn.prepareStatement(sql)) {
			
    		pstat.setString(1, cpf);
    		pstat.setString(2, email);
    		
    		try (ResultSet rs = pstat.executeQuery()) {
    			if(rs.next())
    				return rs.getInt(1) > 0;
    		}
    		
		} catch (SQLException e) {
            System.out.println("Erro ao verificar existência do usuário: " + e.getMessage());
        }
    	
    	return false;
    }
    
    public static Usuario autenticarUsuario(String email, String senha) {
    	String sql = "SELECT nome, cpf, idade, senha FROM USUARIO WHERE email = ? AND senha = ?";
    	
    	try (Connection conn = getConnection(); PreparedStatement pstat = conn.prepareStatement(sql)) {
			
    		pstat.setString(1, email);
    		pstat.setString(2, senha);
    		
    		try (ResultSet rs = pstat.executeQuery()) {
    			if (rs.next()) {
    				String nome = rs.getString("nome");
    				String cpf = rs.getString("cpf");
                    int idade = rs.getInt("idade");
                    
                    return new Usuario(nome, email, senha, idade, cpf);
    			}
    		}
    		
		} catch (SQLException e) {
            System.out.println("Erro ao verificar existência do usuário: " + e.getMessage());
        }
    	
    	return null;
    }
    
}
