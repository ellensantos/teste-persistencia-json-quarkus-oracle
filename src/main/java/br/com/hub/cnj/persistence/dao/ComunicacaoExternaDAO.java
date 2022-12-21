package br.com.hub.cnj.persistence.dao;

import java.math.BigInteger;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import br.com.hub.cnj.persistence.dto.ComunicacaoExternaDTO;

@RequestScoped
public class ComunicacaoExternaDAO {

    EntityManager em;

    private final String listarComunicacaoExterna = "LISTAR_COMUNICACAO_EXTERNA";

    public ComunicacaoExternaDAO(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public ComunicacaoExternaDTO listar(BigInteger id) {
        TypedQuery<ComunicacaoExternaDTO> query = em
                .createNamedQuery(listarComunicacaoExterna, ComunicacaoExternaDTO.class);
                query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return new ComunicacaoExternaDTO();
        } catch (PersistenceException e) {
            throw new PersistenceException(e);
        }
    }

}
