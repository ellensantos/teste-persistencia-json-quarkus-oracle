package br.com.hub.cnj.persistence.dto;

import java.math.BigInteger;

public class ComunicacaoExternaDTO {

    private BigInteger id;
    private String comunicacao;

    public ComunicacaoExternaDTO() {
    }

    public ComunicacaoExternaDTO(BigInteger id, String comunicacao) {
        this.id = id;
        this.comunicacao = comunicacao;
    }

    public BigInteger getId() {
        return this.id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getComunicacao() {
        return this.comunicacao;
    }

    public void setComunicacao(String comunicacao) {
        this.comunicacao = comunicacao;
    }

}
