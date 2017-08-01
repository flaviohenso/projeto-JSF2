package com.flavio.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.flavio.model.Produto;
import com.flavio.util.Paginacao;
import com.flavio.util.jpa.EntityManagerProducer;

public class ProdutoRepository implements Serializable{

	private static final long serialVersionUID = 1L;

	public static Log log = LogFactory.getLog(ProdutoRepository.class);

	@Inject
	private EntityManager entityManager;
	
	private CriteriaQuery<Produto> criteriaQuery;

	private Root<Produto> root;

	private TypedQuery query;
	
	
	public boolean save(Produto produto) throws Exception {
		if (entityManager != null) {
			try {
				EntityManagerProducer.beginTransaction(entityManager);
				this.entityManager.persist(produto);
				return true;
			} catch (Exception e) {
				log.error("Ocorreu um erro ao salvar produto: " + e.getMessage());
				return false;
			}
		} else {
			return false;
		}
	}
	
	public List<Produto> listAll() {
		return entityManager.createNamedQuery("Produto.findAll", Produto.class).getResultList();
	}

	public List<Produto> byNome(String nome) {
		return entityManager.createNamedQuery("Produto.findByNome", Produto.class).setParameter("nome", nome)
				.getResultList();
	}

	@Transactional
	public boolean remover(Produto produto) {
		try {
			produto = this.BuscarPorID(produto.getId());
			EntityManagerProducer.beginTransaction(entityManager);
			entityManager.remove(produto);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			log.error("Produto não pode ser removido! ", e);
		}
		return false;
	}

	public Produto BuscarPorID(Long id) {
		return entityManager.find(Produto.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Produto> filtrados(Paginacao paginacao) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		criteriaQuery = criteriaBuilder.createQuery(Produto.class);
		root = criteriaQuery.from(Produto.class);
		criteriaQuery.select(root);

		if (paginacao.getDescricao() != null) {
			criteriaQuery.where(criteriaBuilder.like(root.get("nome"), "%"+paginacao.getDescricao()+"%"));
		}
		
		query = entityManager.createQuery(this.criteriaQuery);

		query.setFirstResult(paginacao.getPrimeiroRegistro());
		query.setMaxResults(paginacao.getQuantidadeRegistros());

		if (paginacao.isAscendente() && paginacao.getPropriedadeOrdenacao() != null) {
			query = entityManager.createQuery(
					criteriaQuery.orderBy(criteriaBuilder.asc(root.get(paginacao.getPropriedadeOrdenacao()))));
		} else if (paginacao.getPropriedadeOrdenacao() != null) {
			query = entityManager.createQuery(
					criteriaQuery.orderBy(criteriaBuilder.desc(root.get(paginacao.getPropriedadeOrdenacao()))));
		}

		return query.getResultList();

	}

	public int quantidadeFiltrados(Paginacao paginacao) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(Produto.class)));

		return entityManager.createQuery(cq).getSingleResult().intValue();
	}
}
