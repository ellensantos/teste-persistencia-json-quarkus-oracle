package br.com.hub.cnj.persistence.dto;

import java.util.List;

public class RequisicaoDTO {

    private String nome;
    private List<AmigosDTO> amigos;

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
