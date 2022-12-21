package br.com.hub.cnj.persistence.dto;

import java.math.BigInteger;

public class RequisicaoUpdateDTO extends RequisicaoDTO {

    private BigInteger id;

    public BigInteger getId() {
        return this.id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

}
