package dev.otthon.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TesteListagem {

	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.recuperarConexao();
		
		Statement stm = connection.createStatement();
		stm.execute( "SELECT ID, NOME, DESCRICAO FROM PRODUTO" );
		ResultSet resultStatement = stm.getResultSet();
		
		while(resultStatement.next()) {
			Integer id = resultStatement.getInt("ID");
			System.out.println(id);
			
			String nome = resultStatement.getString("NOME");
			System.out.println(nome);
			
			String descricao = resultStatement.getString("DESCRICAO");
			System.out.println(descricao);
			System.out.println("\n");
		}
	
		
		connection.close();
	}

}
