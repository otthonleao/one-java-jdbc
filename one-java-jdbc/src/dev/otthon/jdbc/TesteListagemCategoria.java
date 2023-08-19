package dev.otthon.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dev.otthon.jdbc.dao.CategoriaDAO;
import dev.otthon.jdbc.modelo.CategoriaModel;

public class TesteListagemCategoria {

	public static void main(String[] args) throws SQLException {
		try(Connection connection = new ConnectionFactory().recuperarConexao()) {
			CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
			List<CategoriaModel> listaDeCategorias = categoriaDAO.listar();
			listaDeCategorias.stream().forEach(ct -> System.out.println(ct.getNome()));
		}
	}
}
