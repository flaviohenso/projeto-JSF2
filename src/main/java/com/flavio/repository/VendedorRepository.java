package com.flavio.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.flavio.model.Vendedor;
import com.flavio.util.Paginacao;
import com.flavio.util.jpa.EntityManagerProducer;

public class VendedorRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	public static Log log = LogFactory.getLog(VendedorRepository.class);

	@Inject
	private EntityManager entityManager;
	
	private CriteriaQuery<Vendedor> criteriaQuery;

	private Root<Vendedor> root;

	private TypedQuery query;

	public List<Vendedor> listAll() {
		return entityManager.createNamedQuery("Vendedor.findAll",Vendedor.class).getResultList();
	}

	public boolean save(Vendedor vendedor) throws Exception {
		if (entityManager != null) {
			try {
				EntityManagerProducer.beginTransaction(entityManager);
				entityManager.persist(vendedor);
				return true;
			} catch (Exception e) {
				log.error("Ocorreu um erro ao salvar vendedor: " + e.getMessage());
				return false;
			}
		} else {
			return false;
		}
	}
	
	public Vendedor BuscarPorID(Long id) {
		return entityManager.find(Vendedor.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Vendedor> filtrados(Paginacao paginacao) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		criteriaQuery = criteriaBuilder.createQuery(Vendedor.class);
		root = criteriaQuery.from(Vendedor.class);
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
		cq.select(cb.count(cq.from(Vendedor.class)));

		return entityManager.createQuery(cq).getSingleResult().intValue();
	}

}
