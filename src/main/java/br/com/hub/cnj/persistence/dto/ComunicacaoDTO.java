package br.com.hub.cnj.persistence.dto;

import java.math.BigInteger;
import java.util.Date;

public class ComunicacaoDTO {

    private BigInteger id;
    private String idComunicacao;
    private Date timestampInclusao;
    private String numeroProcesso;
    private String comunicacaoJson;

    public ComunicacaoDTO() {
    }

    public ComunicacaoDTO(BigInteger id, String idComunicacao, Date timestampInclusao, String numeroProcesso,
    String comunicacaoJson) {
        this.id = id;
        this.idComunicacao = idComunicacao;
        this.timestampInclusao = timestampInclusao;
        this.numeroProcesso = numeroProcesso;
        this.comunicacaoJson = comunicacaoJson;
    }

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

    public String getComunicacaoJson() {
        return this.comunicacaoJson;
    }

    public void setComunicacaoJson(String comunicacaoJson) {
        this.comunicacaoJson = comunicacaoJson;
    }

}
