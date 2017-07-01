package com.flavio.conectionDB;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns="/*")
public class HibernateSessionFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// inicia a transação antes de processar o request
		EntityManager em = HibernateUtil.emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			request.setAttribute("entityManager", em);
			
			chain.doFilter(request, response);
			
			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}finally {
			em.close(); //fecha o EntityManager
		}
		
		
	}
	
	@Override
	public void destroy() {
		HibernateUtil.closeEntityManagerFactory(); //fecha a fabrica do EntityManager
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
