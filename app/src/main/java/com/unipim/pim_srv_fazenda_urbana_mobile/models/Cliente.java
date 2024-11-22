package com.unipim.pim_srv_fazenda_urbana_mobile.models;

import java.io.Serializable;
import java.util.Date;

public class Cliente implements Serializable {

    public Integer id;
    public String nome;
    public String cpf;
    public String cnpj;
    public String dataNascimento;
    public String celular;
    public String email;
    public String senha;
    public Boolean premium;
    private String imagem;

    public Cliente(){}


    public Cliente(Integer id, String nome, String cpf, String cnpj, String dataNascimento, String email, String senha, String celular, Boolean premium, String imagem) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.senha = senha;
        this.celular = celular;
        this.premium = premium;
        this.imagem = imagem;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getPremium() {
        return premium;
    }

    public void setPremium(Boolean premium) {
        this.premium = premium;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
