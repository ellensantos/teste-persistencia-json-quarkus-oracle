package br.com.hub.cnj.persistence.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import br.com.hub.cnj.persistence.dto.ComunicacaoDTO;
import br.com.hub.cnj.persistence.models.Comunicacao;

@RequestScoped
public class ComunicacaoDAO {

    EntityManager em;

    private final String listarComunicacao = "LISTAR_COMUNICACAO";

    public ComunicacaoDAO(EntityManager em) {
        this.em = em;
    }

    public List<ComunicacaoDTO> listar() {
        TypedQuery<ComunicacaoDTO> query = em
                .createNamedQuery(listarComunicacao, ComunicacaoDTO.class);
        try {
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenceException(e);
        }
    }

    @Transactional
    public void inserir(List<Comunicacao> comunicacao) {
        try {
            for (Comunicacao comunicacaoDTO : comunicacao) {
                em.persist(comunicacaoDTO);
            }
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw new PersistenceException(e);
        }
    }

}
