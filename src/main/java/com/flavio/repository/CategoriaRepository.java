package com.flavio.repository;

import java.util.List;

import javax.enterprise.context.RequestScoped;
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

import com.flavio.model.Categoria;
import com.flavio.util.Paginacao;
import com.flavio.util.jpa.EntityManagerProducer;

@RequestScoped
public class CategoriaRepository {

	private static final long serialVersionUID = 1L;

	public static Log log = LogFactory.getLog(CategoriaRepository.class);

	@Inject
	private EntityManager entityManager;

	private CriteriaQuery<Categoria> criteriaQuery;

	private Root<Categoria> root;

	private TypedQuery query;

	public boolean save(Categoria categoria) throws Exception {
		if (entityManager != null) {
			try {
				EntityManagerProducer.beginTransaction(entityManager);
				this.entityManager.persist(categoria);
				return true;
			} catch (Exception e) {
				log.error("Ocorreu um erro ao salvar categoria: " + e.getMessage());
				return false;
			}
		} else {
			return false;
		}
	}

	public List<Categoria> listAll() {
		return entityManager.createNamedQuery("Categoria.findAll", Categoria.class).getResultList();
	}

	public List<Categoria> byNome(String nome) {
		return entityManager.createNamedQuery("Categoria.findByNome", Categoria.class).setParameter("nome", nome)
				.getResultList();
	}

	@Transactional
	public boolean remover(Categoria categoria) {
		try {
			categoria = this.BuscarPorID(categoria.getId());
			EntityManagerProducer.beginTransaction(entityManager);
			entityManager.remove(categoria);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			log.error("Categoria não pode ser removido! ", e);
		}
		return false;
	}

	public Categoria BuscarPorID(Long id) {
		return entityManager.find(Categoria.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Categoria> filtrados(Paginacao paginacao) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		criteriaQuery = criteriaBuilder.createQuery(Categoria.class);
		root = criteriaQuery.from(Categoria.class);
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
		cq.select(cb.count(cq.from(Categoria.class)));

		return entityManager.createQuery(cq).getSingleResult().intValue();
	}

}
