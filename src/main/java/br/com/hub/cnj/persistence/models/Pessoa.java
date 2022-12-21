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

import com.fasterxml.jackson.annotation.JsonRawValue;

import br.com.hub.cnj.persistence.dto.PessoaDTO;

@Entity
//@Immutable
@Table(name = "PESSOA")
@NamedNativeQueries({
        @NamedNativeQuery(name = "LISTAR_PESSOA", query = " SELECT ID as id, " +
                " NOME as nome, " +
                " AMIGOS as amigos " +
                " FROM PESSOA ", resultSetMapping = "ListaPessoaSetMapping", resultClass = PessoaDTO.class) })
@SqlResultSetMappings(value = {
        @SqlResultSetMapping(name = "ListaPessoaSetMapping", classes = {
                @ConstructorResult(targetClass = PessoaDTO.class, columns = {
                        @ColumnResult(name = " id", type = BigInteger.class),
                        @ColumnResult(name = "nome", type = String.class),
                        @ColumnResult(name = "amigos", type = String.class),
                })
        })
})

public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private BigInteger id;

    @Column(columnDefinition = "char", length = 100, name = "NOME")
    private String nome;

    @Column(columnDefinition = "json", name = "AMIGOS")
    @JsonRawValue
    //@Type(type = "com.vladmihalcea.hibernate.type.json.JsonType")
    private String amigos;

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
