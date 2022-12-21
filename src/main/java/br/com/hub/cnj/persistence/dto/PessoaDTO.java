package br.com.hub.cnj.persistence.dto;

import java.math.BigInteger;

public class PessoaDTO {

    private BigInteger id;
    private String nome;
    private String amigos;

    public PessoaDTO() {
    }

    public PessoaDTO(BigInteger id, String nome, String amigos) {
        this.id = id;
        this.nome = nome;
        this.amigos = amigos;
    }

    public BigInteger getId() {
        return this.id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAmigos() {
        return this.amigos;
    }

    public void setAmigos(String amigos) {
        this.amigos = amigos;
    }

}
