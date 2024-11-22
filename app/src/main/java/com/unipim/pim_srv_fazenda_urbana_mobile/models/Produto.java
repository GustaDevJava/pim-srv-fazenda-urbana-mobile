package com.unipim.pim_srv_fazenda_urbana_mobile.models;

import java.io.Serializable;

public class Produto implements Serializable {

    public Integer id;
    public String nome;
    public double preco;
    public String categoria;
    public double desconto;
    public String imagem;
    public Integer quantidade;

    public Produto(){}

    public Produto(Integer id, String nome, double preco, double desconto, String categoria, String imagem, Integer quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.desconto = desconto;
        this.categoria = categoria;
        this.imagem = imagem;
        this.quantidade = quantidade;
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}

