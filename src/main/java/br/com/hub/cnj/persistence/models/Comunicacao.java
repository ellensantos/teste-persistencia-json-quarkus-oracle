package br.com.hub.cnj.persistence.models;

import java.math.BigInteger;
import java.util.Date;

import javax.json.Json;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.JsonNode;

import br.com.hub.cnj.persistence.dto.ComunicacaoDTO;

@Entity
@Table(name = "COMUNICACAO")
@NamedNativeQueries({
        @NamedNativeQuery(name = "LISTAR_COMUNICACAO", query = " SELECT ID as id, " +
                " ID_COMUNICACAO as idComunicacao, " +
                " TIMESTAMP_INCLUSAO as tsInclusao, " +
                " NUMERO_PROCESSO as numeroProcesso, " +
                " COMUNICACAO as comunicacao " +
                " FROM COMUNICACAO " +
                " ORDER BY 1 ASC " , resultSetMapping = "ListaComunicacaoSetMapping", resultClass = ComunicacaoDTO.class) })
@SqlResultSetMappings(value = {
        @SqlResultSetMapping(name = "ListaComunicacaoSetMapping", classes = {
                @ConstructorResult(targetClass = ComunicacaoDTO.class, columns = {
                        @ColumnResult(name = " id", type = BigInteger.class),
                        @ColumnResult(name = "idComunicacao", type = String.class),
                        @ColumnResult(name = "tsInclusao", type = Date.class),
                        @ColumnResult(name = "numeroProcesso", type = String.class),
                        @ColumnResult(name = "comunicacao", type = String.class),
                })
        })
})
public class Comunicacao {

    @EmbeddedId
    private ComunicacaoPK comunicacaoPK;

    @Column(name = "TIMESTAMP_INCLUSAO")
    @Temporal(TemporalType.DATE)
    private Date timestampInclusao;

    @Column(columnDefinition = "char", length = 100, name = "NUMERO_PROCESSO")
    private String numeroProcesso;

    @Column(columnDefinition = "clob", name = "COMUNICACAO")
    private String comunicacaoJson;

    public ComunicacaoPK getComunicacaoPK() {
        return this.comunicacaoPK;
    }

    public void setComunicacaoPK(ComunicacaoPK comunicacaoPK) {
        this.comunicacaoPK = comunicacaoPK;
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
