/**
 * projeto-web : 9 de ago de 2017 
 */
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

import com.flavio.model.Fornecedor;
import com.flavio.util.Paginacao;
import com.flavio.util.jpa.EntityManagerProducer;

/**
 * @author flavio
 *
 */
public class FornecedorRepository implements GenericRepository<Fornecedor, Serializable>, Serializable {

	private static final long serialVersionUID = 1L;

	public static Log log = LogFactory.getLog(FornecedorRepository.class);

	@Inject
	private EntityManager entityManager;

	private CriteriaQuery<Fornecedor> criteriaQuery;

	private Root<Fornecedor> root;

	private TypedQuery query;

	@Override
	public List<Fornecedor> filtrados(Paginacao paginacao) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		criteriaQuery = criteriaBuilder.createQuery(Fornecedor.class);
		root = criteriaQuery.from(Fornecedor.class);
		criteriaQuery.select(root);

		if (paginacao.getDescricao() != null) {
			criteriaQuery.where(criteriaBuilder.like(root.get("nome"), "%" + paginacao.getDescricao() + "%"));
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
		} else {
			query = entityManager.createQuery(
					criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id"))));
		}

		return query.getResultList();
	}

	@Override
	public int quantidadeFiltrados(Paginacao paginacao) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(Fornecedor.class)));

		return entityManager.createQuery(cq).getSingleResult().intValue();
	}

	@Override
	public boolean save(Fornecedor fornecedor) throws Exception {
		if (entityManager != null) {
			try {
				EntityManagerProducer.beginTransaction(entityManager);
				this.entityManager.persist(fornecedor);
				return true;
			} catch (Exception e) {
				log.error("Ocorreu um erro ao salvar fornecedor: " + e.getMessage());
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public List<Fornecedor> listAll() {
		return entityManager.createNamedQuery("Fornecedor.findAll", Fornecedor.class).getResultList();
	}

	@Override
	public boolean remover(Fornecedor fornecedor) {
		fornecedor = this.BuscarPorID(fornecedor.getId());
		EntityManagerProducer.beginTransaction(entityManager);
		entityManager.remove(fornecedor);
		entityManager.flush();
		return true;
	}

	@Override
	public Fornecedor BuscarPorID(Long id) {
		return entityManager.find(Fornecedor.class, id);
	}

}
