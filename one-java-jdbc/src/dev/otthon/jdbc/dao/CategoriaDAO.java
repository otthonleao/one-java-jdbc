package dev.otthon.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import dev.otthon.jdbc.modelo.CategoriaModel;

public class CategoriaDAO {
	private Connection connection;
	
	public CategoriaDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<CategoriaModel> listar() throws SQLException {
		List<CategoriaModel> categorias = new ArrayList<>();
		
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
}
