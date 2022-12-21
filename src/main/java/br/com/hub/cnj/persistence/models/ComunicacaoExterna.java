package br.com.hub.cnj.persistence.models;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import com.fasterxml.jackson.annotation.JsonRawValue;

import br.com.hub.cnj.persistence.dto.ComunicacaoExternaDTO;

@Entity
@Immutable
@Table(name = "COMUNICACAO_EXTERNA")
@NamedNativeQueries({
        @NamedNativeQuery(name = "LISTAR_COMUNICACAO_EXTERNA", query = " SELECT ID as id, " +
                " COMUNICACAO as comunicacao " +
                " FROM COMUNICACAO_EXTERNA " +
                " WHERE " +
                " ID = :id ", resultSetMapping = "ListaComunicacaoExternaSetMapping", resultClass = ComunicacaoExternaDTO.class) })
@SqlResultSetMappings(value = {
        @SqlResultSetMapping(name = "ListaComunicacaoExternaSetMapping", classes = {
                @ConstructorResult(targetClass = ComunicacaoExternaDTO.class, columns = {
                        @ColumnResult(name = " id", type = BigInteger.class),
                        @ColumnResult(name = "comunicacao", type = String.class),
                })
        })
})
public class ComunicacaoExterna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private BigInteger id;

    @Column(columnDefinition = "clob", name = "COMUNICACAO")
    @JsonRawValue
    // @Type(type = "com.vladmihalcea.hibernate.type.json.JsonType")
    private String comunicacao;

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
