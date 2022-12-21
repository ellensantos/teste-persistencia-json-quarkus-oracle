package br.com.hub.cnj.persistence.models;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ComunicacaoPK implements Serializable{

    @Column(name = "ID")
    private BigInteger id;

    @Column(columnDefinition = "char", length = 150, name = "ID_COMUNICACAO")
    private String idComunicacao;

    public BigInteger getId() {
        return this.id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getIdComunicacao() {
        return this.idComunicacao;
    }

    public void setIdComunicacao(String idComunicacao) {
        this.idComunicacao = idComunicacao;
    }

}
