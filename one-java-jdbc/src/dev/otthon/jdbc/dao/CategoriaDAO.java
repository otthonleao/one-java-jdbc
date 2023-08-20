package dev.otthon.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.otthon.jdbc.modelo.CategoriaModel;
import dev.otthon.jdbc.modelo.ProdutoModel;

public class CategoriaDAO {
	private Connection connection;
	
	public CategoriaDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<CategoriaModel> listar() throws SQLException {
		List<CategoriaModel> categorias = new ArrayList<>();
		
		System.out.println("==== EXECUTANDO A QUERY DE LISTAR CATEGORIA ====");
		
		String sql = "SELECT ID, NOME FROM CATEGORIA";
		
		try(PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.execute();
			
			try(ResultSet rst = pstm.getResultSet()) {
				while(rst.next()) {
					CategoriaModel categoria = new CategoriaModel(rst.getInt(1), rst.getString(2));
					categorias.add(categoria);
				}
			}
		}
		return categorias;
	}

	public List<CategoriaModel> listarComProdutos() throws SQLException {
		
		CategoriaModel ultima = null;
		List<CategoriaModel> categorias = new ArrayList<>();
		
		System.out.println("==== EXECUTANDO A QUERY DE LISTAR CATEGORIA ====");
		
		String sql = "SELECT C.ID, C.NOME, P.ID, P.NOME, P.DESCRICAO FROM CATEGORIA C INNER JOIN PRODUTO P ON C.ID = P.CATEGORIA_ID";
		
		try(PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.execute();
			
			try(ResultSet rst = pstm.getResultSet()) {
				while(rst.next()) {
					//Para não repetir o nome da categoria que já tenha sido impressa
					if(ultima == null || !ultima.getNome().equals(rst.getString(2))) {
						CategoriaModel categoria = new CategoriaModel(rst.getInt(1), rst.getString(2));
						ultima = categoria;
						categorias.add(categoria);
					}
					ProdutoModel produto = new ProdutoModel(rst.getInt(3), rst.getString(4), rst.getString(5));
					ultima.adicionar(produto);
				}
			}
		}
		return categorias;
	}
}
