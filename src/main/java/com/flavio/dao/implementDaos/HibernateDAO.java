package com.flavio.dao.implementDaos;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.flavio.dao.interfaceDao.GenericDAO;
import com.flavio.util.jpa.EntityManagerProducer;

/*
 * Classe que implementa os metodos de acesso ao banco de dados
 */
@RequestScoped
public abstract class HibernateDAO<T, Type extends Serializable> implements GenericDAO<T, Type> {

	private Class<T> persistentClass;
	private CriteriaQuery<T> criteriaQuery;
	private Root<T> root;
	private TypedQuery<T> query;
	
	@SuppressWarnings("unchecked")
	public HibernateDAO(Class persistentClass) {

		super();

		this.persistentClass = persistentClass;
	}

	// public void beginTransaction() {
	//
	// HibernateUtil.beginTransaction();
	//
	// }
	//
	// public void commitTransaction() {
	//
	// HibernateUtil.commitTransaction();
	//
	// }
	//
	
	public void save(T entity, EntityManager entityManager) throws Exception{
		if (entityManager != null) {
				System.out.println("Enti dentro do seve OK");
				entityManager.persist(entity);
		}else {
			System.out.println("resolver problema no entity igual a null no save]");
		}
		
		
		/**
		 * forma de acessar o entityManager pela opensessionview
		 * ((EntityManager) EntityManagerProducer.getRequestAtribute("entityManager")).persist(entity); 
		 */

	}

	public List<T> listAllD(EntityManager entityManager) {// por ordem de inserção
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		this.criteriaQuery = criteriaBuilder.createQuery(this.persistentClass);
		this.root = criteriaQuery.from(this.persistentClass);
		this.criteriaQuery.select(this.root); // necessário caso uma condição
												// where seja adicionada na
												// consulta
		this.query = entityManager.createQuery(this.criteriaQuery);
		return this.query.getResultList();
	}

	public List<T> listAllascD(String coluna, EntityManager entityManager) {// por ordem acendente

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		this.criteriaQuery = criteriaBuilder.createQuery(this.persistentClass);
		this.root = criteriaQuery.from(this.persistentClass);
		this.criteriaQuery.select(this.root); // necessário caso uma condição
												// where seja adicionada na
												// consulta
		this.query = entityManager.createQuery(this.criteriaQuery.orderBy(
				criteriaBuilder.asc(root.get(coluna))));
		return this.query.getResultList();

	}

	public T objetoUnicoD(Integer value, String coluna, EntityManager entityManager) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		this.criteriaQuery = criteriaBuilder.createQuery(this.persistentClass);
		this.root = criteriaQuery.from(this.persistentClass);
		Expression<? extends Number> codigo = root.get(coluna);
		Predicate condicao = criteriaBuilder.equal(codigo, value); // rever essa
																	// consulta
		this.criteriaQuery.select(this.root).where(condicao);

		this.query = entityManager.createQuery(this.criteriaQuery);

		T singleResult = this.query.getSingleResult();

		return singleResult;
	}

	// public T findById(Long id) {
	// return HibernateUtil.getConnection().creat
	// }

	// public Pessoa findById(long id) {
	// try {
	// return (Pessoa) getSession().createCriteria(Pessoa.class)
	// .add(Restrictions.idEq(id)).uniqueResult();
	//
	// //aqui usamos o metodo add() para adicionar
	// //um criterio de busca. Usamos como criterio
	// //um objeto do tipo Restrictions e ele nos
	// //formece inumeros outros metodos.
	// //Como a consulta é por id, vamos usar o idEq(),
	// //seria um idEquals. Como vamos retornar apenas um
	// //resultado, devemos indicar com o metodo uniqueResult().
	//
	// } finally {
	// //Fechamos a sess&atilde;o
	// close();
	// }
	// }

	// public T get(T entity){
	// return ;
	// }

}
