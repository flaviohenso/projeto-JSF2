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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.flavio.model.Cliente;
import com.flavio.util.Paginacao;
import com.flavio.util.jpa.EntityManagerProducer;

public class ClienteRepository implements GenericRepository<Cliente, Serializable>, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager entityManager;

	public static Log log = LogFactory.getLog(ClienteRepository.class);

	private CriteriaQuery<Cliente> criteriaQuery;
 
	private Root<Cliente> root;

	private TypedQuery query;

	@Override
	public List<Cliente> filtrados(Paginacao paginacao) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
		root = criteriaQuery.from(Cliente.class);
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
		}

		return query.getResultList();
	}

	@Override
	public int quantidadeFiltrados(Paginacao paginacao) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(Cliente.class)));

		return entityManager.createQuery(cq).getSingleResult().intValue();
	}

	@Override
	public boolean save(Cliente cliente) throws Exception {
		if (entityManager != null) {
			try {
				EntityManagerProducer.beginTransaction(entityManager);
				entityManager.merge(cliente);
				return true;
			} catch (Exception e) {

				log.error("Ocorreu um erro ao salvar cliente: " + e.getMessage());
				return false;
			}
		}
		return false;
	}

	@Override
	public List<Cliente> listAll() {

		return entityManager.createNamedQuery("Cliente.findAll", Cliente.class).getResultList();
	}

	@Override
	public boolean remover(Cliente cliente) {
		try {
			cliente = this.BuscarPorID(cliente.getId());
			EntityManagerProducer.beginTransaction(entityManager);
			entityManager.remove(cliente);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			log.error("Cliente não pode ser removido! ", e);
		}
		return false;
	}

	@Override
	public Cliente BuscarPorID(Long id) {
		return entityManager.find(Cliente.class, id);
	}

}
