package dev.otthon.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TesteInsertAutoCommit {
	
	public static void main(String[] args) throws SQLException {
			
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.recuperarConexao();
		
		connection.setAutoCommit(false);
		
		try {
					PreparedStatement stm = connection.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
					adicionarVariavel("Celular", "iPhone 14", stm);
					adicionarVariavel("Smartphone", "Samsumg Galaxy 22", stm);
					
					connection.commit();
					stm.close();
					connection.close();
		} catch (Exception e) {
					e.printStackTrace();
					System.out.println("ROLLBACK EXECUTANDO");
					connection.rollback();
		}
	}

	private static void adicionarVariavel(String nome, String descricao, PreparedStatement stm) throws SQLException {
		stm.setString(1, nome);
		stm.setString(2, descricao);
		
		// FORÇANDO UM ERRO PARA TESTAR A EXCEPTION
//		if(nome.equals("Celular")) {
//				throw new RuntimeException("Não foi possível adicionar o produto");
//		}


		stm.execute();
		ResultSet rst = stm.getGeneratedKeys();
		while(rst.next()) {
			Integer id = rst.getInt(1); // Feito com o "Int columnIndex"
			System.out.println("O ID do produto inserido é: " + id);
		}
	}
}
