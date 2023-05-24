package Dto;
public class Produto {
    private int id;
    private String nome;
    private String fabricante;
    private String concentracao;
    private double preco;
    private int qtd;
    
     public Produto() {

    }
    public Produto(String nome, String fabricante, String concentracao, double preco, int qtd) {
        this.nome = nome;
        this.fabricante = fabricante;
        this.concentracao = concentracao;
        this.preco = preco;
        this.qtd = qtd;
    }
        public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getConcentracao() {
        return concentracao;
    }

    public void setConcentracao(String concentracao) {
        this.concentracao = concentracao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
       @Override
    public String toString() {
        return getNome(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
