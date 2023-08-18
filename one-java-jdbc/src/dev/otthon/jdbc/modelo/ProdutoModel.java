package dev.otthon.jdbc.modelo;

public class ProdutoModel {
	private Integer id;
    private String nome;
    private String descricao;

    public ProdutoModel(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }
    
    public ProdutoModel(Integer id, String nome, String descricao) {
    	this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
		
	//Quando o objeto instanciado dessa classe for chamado pelo
    //System.out.println(objeto) será impresso dessa forma ao invés do
    //endereço de memória.
    @Override
    public String toString() {
        return String.format("O produto criado é: %d, %s, %s", this.id, this.nome, this.descricao);
    }
}
