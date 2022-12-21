package br.com.hub.cnj.persistence.dao;

import java.math.BigInteger;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import br.com.hub.cnj.persistence.dto.PessoaDTO;
import br.com.hub.cnj.persistence.models.Pessoa;

@RequestScoped
public class PessoaDAO {

    EntityManager em;

    private final String listarPessoa = "LISTAR_PESSOA";

    public PessoaDAO(EntityManager em) {
        this.em = em;
    }

    public List<PessoaDTO> listar() {
        TypedQuery<PessoaDTO> query = em
                .createNamedQuery(listarPessoa, PessoaDTO.class);

        try {
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenceException(e);
        }
    }

    @Transactional
    public void inserir(Pessoa pessoa) {
        try {
            em.persist(pessoa);
        } catch (PersistenceException e) {
            throw new PersistenceException(e);
        }
    }

    @Transactional
    public void atualizar(Pessoa pessoa) {
        try {
            Pessoa pessoaPesquisada = em.find(Pessoa.class, pessoa.getId());
            pessoaPesquisada.setAmigos(pessoa.getAmigos());
            pessoaPesquisada.setNome(pessoa.getNome());
        } catch (PersistenceException e) {
            throw new PersistenceException(e);
        }
    }

    @Transactional
    public Pessoa pesquisar(BigInteger id) {
        try {
            Pessoa pessoa = em.find(Pessoa.class, id);
            return pessoa;
        } catch (PersistenceException e) {
            throw new PersistenceException(e);
        }
    }

    @Transactional
    public void excluir(BigInteger id) {
        try {
            Pessoa pessoa = pesquisar(id);
            em.remove(pessoa);
        } catch (PersistenceException e) {
            throw new PersistenceException(e);
        }
    }
}
