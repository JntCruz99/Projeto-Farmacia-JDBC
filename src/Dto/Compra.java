package Dto;

import java.util.ArrayList;
import java.util.List;

public class Compra {

    private int id;
    private Cliente cliente;
    private List<Produto> produtos;
    private String status;
    private String forma_pagamento;
    private int qtd_itens;

    public Compra() {

    }

    public Compra(int id, Cliente cliente, List<Produto> produtos, String status, String forma_pagamento, int qtd_itens) {
        this.id = id;
        this.cliente = cliente;
        this.produtos = produtos;
        this.status = status;
        this.forma_pagamento = forma_pagamento;
        this.qtd_itens = qtd_itens;
    }

    public int getQtd_itens() {
        return qtd_itens;
    }

    public void setQtd_itens(int qtd_itens) {
        this.qtd_itens = qtd_itens;
    }

 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getForma_pagamento() {
        return forma_pagamento;
    }

    public void setForma_pagamento(String forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }

    public Compra(Cliente cliente) {
        this.cliente = cliente;
        this.produtos = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public void removerProduto(Produto produto) {
        produtos.remove(produto);
    }

    public double calcularTotal() {
        double total = 0;
        for (Produto produto : produtos) {
            total += produto.getPreco();
        }
        return total;
    }

    // Getters e setters
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public String getClienteCPF() {
        return cliente.getCpf();
    }
    
    public String getNomeCliente() {
    return cliente.getNome();
    }
    
}
