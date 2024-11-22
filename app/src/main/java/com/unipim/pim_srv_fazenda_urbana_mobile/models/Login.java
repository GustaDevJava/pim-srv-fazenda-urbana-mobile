package com.unipim.pim_srv_fazenda_urbana_mobile.models;

import java.io.Serializable;

public class Login implements Serializable {

    private String email;
    private String senha;

    public Login(){}

    public Login(String email, String senha){
        this.email = email;
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
