package dev.otthon.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dev.otthon.jdbc.dao.ProdutoDAO;
import dev.otthon.jdbc.modelo.ProdutoModel;

public class TesteInsertViaModeloDaoEListagem {
	public static void main(String[] args) throws SQLException {
		
		ProdutoModel tv = new ProdutoModel("TV", "LG SMART TV 50");
		
		try(Connection connection = new ConnectionFactory().recuperarConexao()) {
			ProdutoDAO produtoDao = new ProdutoDAO(connection);
			
			produtoDao.salvar(tv);
			
			List<ProdutoModel> listaDeProdutos = produtoDao.listar();
			listaDeProdutos.stream().forEach(lp -> System.out.println(lp));
		}
	}
}
