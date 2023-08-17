package dev.otthon.jdbc;

import java.sql.Connection;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.SQLException;
import javax.sql.DataSource;

public class ConnectionFactory {
	
	public DataSource dataSource;

	public ConnectionFactory() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/one_loja_virtual?useTimezone=true&serverTimezone=UTC");
		comboPooledDataSource.setUser("root");
		comboPooledDataSource.setPassword("");
		
		/** NÚMERO MÁXIMO DE CONEXÕES */
		comboPooledDataSource.setMaxPoolSize(15);
		
		this.dataSource = comboPooledDataSource;
		
	}
	
	public Connection recuperarConexao() throws SQLException {
		return this.dataSource.getConnection();
	}
}

