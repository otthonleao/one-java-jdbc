package dev.otthon.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import dev.otthon.jdbc.dao.ProdutoDAO;
import dev.otthon.jdbc.modelo.ProdutoModel;

public class TesteInsertViaProdutoModelUsandoDao {
	public static void main(String[] args) throws SQLException {
		ProdutoModel tv = new ProdutoModel("TV", "LG SMART TV 50");
		try(Connection connection = new ConnectionFactory().recuperarConexao()) {
			ProdutoDAO produtoDao = new ProdutoDAO(connection);
			produtoDao.salvar(tv);
			
			System.out.println(tv);
		}
	}
}
