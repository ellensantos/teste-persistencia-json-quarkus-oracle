package br.com.hub.cnj.persistence.dto;

import java.math.BigInteger;
import java.util.List;

public class PessoaCompletaDTO {

    private BigInteger id;
    private String nome;
    private List<AmigosDTO> amigos;

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

    public List<AmigosDTO> getAmigos() {
        return this.amigos;
    }

    public void setAmigos(List<AmigosDTO> amigos) {
        this.amigos = amigos;
    }

}
