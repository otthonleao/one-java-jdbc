package dev.otthon.jdbc;

import dev.otthon.jdbc.modelo.ProdutoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TesteInsertComModeloProduto {
    public static void main(String[] args) throws SQLException {
        ProdutoModel monitor = new ProdutoModel("monitor", "LG Ultrawide 29");

        try(Connection connection = new ConnectionFactory().recuperarConexao()){
            String sql = "INSERT INTO PRODUTO (NOME, DESCRICAO) VALUES (?, ?)";
            try(PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.setString(1, monitor.getNome());
                pstm.setString(2, monitor.getDescricao());
                pstm.execute();

                /** RECUPERAR A CHAVE GERADA */
                try(ResultSet rst = pstm.getGeneratedKeys()) {
                    while(rst.next()) {
                        monitor.setId(rst.getInt(1));
                    }
                }
            }
        }
        System.out.println(monitor);
    }
}
