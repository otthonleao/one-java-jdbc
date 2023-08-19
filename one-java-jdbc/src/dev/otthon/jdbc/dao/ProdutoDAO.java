package dev.otthon.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import dev.otthon.jdbc.modelo.CategoriaModel;
import dev.otthon.jdbc.modelo.ProdutoModel;

public class ProdutoDAO {
	private Connection connection;
	
	public ProdutoDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void salvar(ProdutoModel produtoModel) throws SQLException {
		String sql = "INSERT INTO PRODUTO (NOME, DESCRICAO) VALUES (?, ?)";
        try(PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, produtoModel.getNome());
            pstm.setString(2, produtoModel.getDescricao());
            pstm.execute();

            /** RECUPERAR A CHAVE GERADA */
            try(ResultSet rst = pstm.getGeneratedKeys()) {
                while(rst.next()) {
                    produtoModel.setId(rst.getInt(1));
                }
            }
        }
	}
	
	public List<ProdutoModel> listar() throws SQLException {
		List<ProdutoModel> produtoModels = new ArrayList<ProdutoModel>();
		
		String sql = "SELECT id, nome, descricao FROM produto";
		
		try(PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.execute();
			
			try(ResultSet rst = pstm.getResultSet()) {
				while(rst.next()) {
					ProdutoModel produtoModel = new ProdutoModel(rst.getInt(1),
							rst.getString(2), rst.getString(3));
					produtoModels.add(produtoModel);
				}
				
			}
		}
		return produtoModels;
	}

	public List<ProdutoModel> buscar(CategoriaModel ct) throws SQLException {
		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		
		System.out.println("#### EXECUTANDO A QUERY DE BUSCAR PRODUTO POR CATEGORIA ####");
		
		String sql = "SELECT id, nome, descricao FROM produto WHERE categoria_id = ?";
		
		try(PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.setInt(1,  ct.getId());
			pstm.execute();
			
			try(ResultSet rst = pstm.getResultSet()) {
				while(rst.next()) {
					ProdutoModel produto = new ProdutoModel(rst.getInt(1),
							rst.getString(2), rst.getString(3));
					produtos.add(produto);
				}
				
			}
		}
		return produtos;
	}
}
