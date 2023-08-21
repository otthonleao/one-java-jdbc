![one-tbr5](https://github.com/otthonleao/one-java-jdbc/assets/54039360/4501c097-b102-4759-ade0-dabc0ca393c4)
# Java Database Connectivity
Estudos para trabalhar com Banco de Dados relacional, além do Statement e do ResultSet, encapsulando o acesso em um DAO, Connection pool, datasources e outros recursos importantes.

O JDBC é uma API que simplifica o acesso e trocas entre bancos de dados com a aplicação. A partir disso, não é mais necessário conhecer o protocolo, saber os detalhes técnicos, nem ficar abrindo o socket e fazendo uma comunicação manual com o banco de dados, basta utilizar o respeqctivo drive **.jar** 

## Banco de Dados Relacional
O SGDB utlizado no treinamento é o MySQL, no qual foram criados um squema `one_loja_virtual`, uma table `produto` - com colunas de _id_, _nome_, _descricao_, _categoria_id_ - que se relaciona por meio de uma chave estrangeira com a chave primária da table `categoria`.
```SQL
CREATE DATABASE one_loja_virtual;

CREATE TABLE produto (
    id INT AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    descricao VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE = InnoDB;

#INSERINDO VALORES NA TABELA PRODUTO
INSERT INTO PRODUTO (nome, descricao) VALUES ('Laptop', 'MSI Prestigie 14');
INSERT INTO PRODUTO (nome, descricao) VALUES ('Laptop', 'Apple Macbook Air');
INSERT INTO PRODUTO (nome, descricao) VALUES ('TV', 'SmartTV Samsung 50');
INSERT INTO PRODUTO (nome, descricao) VALUES ('Monitor', 'LG Ultrawide 29');
INSERT INTO PRODUTO (nome, descricao) VALUES ('Smartphone', 'iPhone 13 128GB Silver');

CREATE TABLE categoria (
	id INT AUTO_INCREMENT,
	nome VARCHAR (50) NOT NULL,
	PRIMARY KEY (id)
) Engine InnoDB;

#INSERINDO VALORES NA TABELA CATEGORIA
INSERT INTO CATEGORIA (nome) VALUES ('ELETRONICOS');
INSERT INTO CATEGORIA (nome) VALUES ('TV E MONITORES');
INSERT INTO CATEGORIA (nome) VALUES ('TELEFONIA');
INSERT INTO CATEGORIA (nome) VALUES ('INFORMÁTICA');

#CRIANDO UMA COLUNA NA TABELA PRODUTOS PARA RECEBER A REFERÊNCIA DA CATEGORIA
ALTER TABLE PRODUTO ADD COLUMN CATEGORIA_ID INT;
ALTER TABLE PRODUTO ADD FOREIGN KEY (CATEGORIA_ID) REFERENCES CATEGORIA (ID);

#SETANDO O PK DA TABELA CATEGORIA PARA SER REFERÊNCIA DO FK DA TABELA PRODUTO 
UPDATE PRODUTO SET CATEGORIA_ID = 4 WHERE ID = 1;
UPDATE PRODUTO SET CATEGORIA_ID = 4 WHERE ID = 2;
UPDATE PRODUTO SET CATEGORIA_ID = 2 WHERE ID = 3;
UPDATE PRODUTO SET CATEGORIA_ID = 2 WHERE ID = 4;
UPDATE PRODUTO SET CATEGORIA_ID = 3 WHERE ID = 5;

SELECT * FROM CATEGORIA C INNER JOIN PRODUTO P ON C.ID = P.CATEGORIA_ID;
```
## Conexão
![](https://github.com/otthonleao/one-java-jdbc/blob/main/datasource-java-jdbc.jpg?raw=true)
### Factory Method com Datasource
Factory Method trata-se de um _design patterns_ que tem por objetivo **centralizar** e **encapsular** um código que vai criar um objeto _Connection_. Logo, é feito uma conexão em uma classe isolada e chamá-la sempre que necessário, além disso caso seja necessário mudar o SGDB - ou até mesmo modificar o usuário e senha - pode-se executar a alteração direto na classe “Fábrica de Conexão".

O **datasource** facilita e controla o número de conexões feitas ao JDBC, assim é possível organizar a fila de requisições dos usuário automaticamente. O objetivo do **Pool de Conexões** é ter um controle maior da conexões, de quantas conexões vão estar abertas, evitando de abrirmos e fecharmos conexões descontroladamente, além de enfileirar as requisições 
```Java
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
  public DataSource dataSource;

  public ConnectionFactory() {
    ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
    comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/one_loja_virtual?useTimezone=true&serverTimezone=UTC");
    comboPooledDataSource.setUser("root");
    comboPooledDataSource.setPassword("");
		
    comboPooledDataSource.setMaxPoolSize(15);
    this.dataSource = comboPooledDataSource;
  }

  public Connection recuperarConexao() throws SQLException {
    return this.dataSource.getConnection();
  }
}
```
## Model
Um package modelo é uma representação da tabela no SGDB, no nosso exemplo é a representatividade da tabela '**produto**'. Dentro classe modelo fazemos um construtor para passar os parâmetros que irão para o banco e também os _getters_ e _setters_ necessários.

## View

## Controller








