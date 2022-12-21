package br.com.hub.cnj.persistence.dto;

import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class ComunicacaoCompletaDTO {

    private BigInteger id;
    private String idComunicacao;
    private Date timestampInclusao;
    private String numeroProcesso;
    private ObjectNode comunicacaoJson;

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

    public Date getTimestampInclusao() {
        return this.timestampInclusao;
    }

    public void setTimestampInclusao(Date timestampInclusao) {
        this.timestampInclusao = timestampInclusao;
    }

    public String getNumeroProcesso() {
        return this.numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public ObjectNode getComunicacaoJson() {
        return this.comunicacaoJson;
    }

    public void setComunicacaoJson(ObjectNode comunicacaoJson) {
        this.comunicacaoJson = comunicacaoJson;
    }

}
