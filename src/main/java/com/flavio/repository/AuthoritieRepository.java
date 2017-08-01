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

import com.flavio.model.Authoritie;
import com.flavio.util.Paginacao;
import com.flavio.util.jpa.EntityManagerProducer;

public class AuthoritieRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	public static Log log = LogFactory.getLog(AuthoritieRepository.class);

	@Inject
	private EntityManager entityManager;

	private CriteriaQuery<Authoritie> criteriaQuery;

	private Root<Authoritie> root;

	private TypedQuery query;

	public boolean save(Authoritie authoritie) throws Exception {
		if (entityManager != null) {
			try {
				EntityManagerProducer.beginTransaction(entityManager);
				this.entityManager.persist(authoritie);
				return true;
			} catch (Exception e) {
				log.error("Ocorreu um erro ao salvar authority: " + e.getMessage());
				return false;
			}
		} else {
			return false;
		}
	}

	public List<Authoritie> listAll() {
		return entityManager.createNamedQuery("Authorities.findAll", Authoritie.class).getResultList();
	}

	public List<Authoritie> byNome(String nome) {
		return entityManager.createNamedQuery("Authorities.findByNome", Authoritie.class).setParameter("nome", nome)
				.getResultList();
	}

	@Transactional
	public boolean remover(Authoritie authoritie) {
		try {
			authoritie = this.BuscarPorID(authoritie.getId());
			EntityManagerProducer.beginTransaction(entityManager);
			entityManager.remove(authoritie);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			log.error("Atuhoritie não pode ser removido! ", e);
		}
		return false;
	}

	public Authoritie BuscarPorID(Long id) {
		return entityManager.find(Authoritie.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Authoritie> filtrados(Paginacao paginacao) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		criteriaQuery = criteriaBuilder.createQuery(Authoritie.class);
		root = criteriaQuery.from(Authoritie.class);
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
		cq.select(cb.count(cq.from(Authoritie.class)));

		return entityManager.createQuery(cq).getSingleResult().intValue();
	}

	private CriteriaQuery<Authoritie> criarCriteriaParaFiltro(Paginacao paginacao) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		criteriaQuery = criteriaBuilder.createQuery(Authoritie.class);
		this.root = criteriaQuery.from(Authoritie.class);
		criteriaQuery.select(this.root);

		// if (StringUtils.isNotEmpty(filtro.getDescricao())) {
		// criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(),
		// MatchMode.ANYWHERE));
		// }

		return criteriaQuery;
	}

	// metodo que efetuará a paginação junto ao banco de dados
	// deve-se setar a quantidade total em "totalNumberOfEntries"
	// em seguida utiliza-se o firstResult e maxResult,
	// mas pode depender do banco de dados utilizado
	// e adicione a colecao de resultado em "dataList"
	// public void listarDados(Paginacao vlhInfo) {
	//
	// Number count = (Number)
	// entityManager.createNamedQuery("Authorities.count", Authoritie.class);
	//
	// vlhInfo.setTotalNumberOfEntries(count.intValue());
	//
	// Integer firstResult = vlhInfo.getPagingPage() *
	// vlhInfo.getPagingNumberPer();
	// Integer maxResult = vlhInfo.getPagingNumberPer();
	//
	// Integer fromIndex = firstResult;
	// Integer toIndex = firstResult + maxResult;
	//
	// if (toIndex > count.intValue()) {
	// toIndex = count.intValue();
	// }
	//
	// //List<Authoritie> result = entidades.subList(fromIndex,
	// toIndex);//consulta no banco
	// //vlhInfo.setDataList(result);
	// }
}
