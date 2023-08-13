package dev.otthon.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class TesteConexao {

	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.recuperarConexao();
		
		System.out.println("FIM DA CONEXÃO COM O MySQL");
		
		connection.close();
	}

}
