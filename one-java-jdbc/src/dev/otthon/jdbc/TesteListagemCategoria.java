package dev.otthon.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dev.otthon.jdbc.dao.CategoriaDAO;
import dev.otthon.jdbc.dao.ProdutoDAO;
import dev.otthon.jdbc.modelo.CategoriaModel;
import dev.otthon.jdbc.modelo.ProdutoModel;

public class TesteListagemCategoria {

	public static void main(String[] args) throws SQLException {
		try(Connection connection = new ConnectionFactory().recuperarConexao()) {
			CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
			
			List<CategoriaModel> listaDeCategorias = categoriaDAO.listar();
			listaDeCategorias.stream().forEach(ct -> {
				System.out.println(ct.getNome());
				
				try {
					for(ProdutoModel produto : new ProdutoDAO(connection).buscar(ct)) {
						System.out.println(ct.getNome() + " - " + produto.getNome() + ": " + produto.getDescricao());
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});
		}
	}
}
