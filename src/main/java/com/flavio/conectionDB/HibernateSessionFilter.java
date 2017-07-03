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

		/*
		 *  inicia a transação antes de processar o request
		 */
		EntityManager em = JpaUtil.getConnection();
		try {
			
			/*
			 * Inicia a transaction
			 */
			JpaUtil.beginTransaction(em);
			
			/*
			 * Seta o um atributo no request (esse atributo é o entityManager), para ser obtido posteriormente,
			 * ondem precisar
			 */
			request.setAttribute("entityManager", em);
			
			chain.doFilter(request, response);
			
			/*
			 * Comita a transaction
			 */
			JpaUtil.commitTransaction(em);
		} catch (Exception e) {
			
			/*
			 * Verifica em caso de exception se o EntityManager esta aberta, estando aberta chama o metodo de RollBack
			 */
			if (em.isOpen()) {
				JpaUtil.rollBackTransaction(em);
			}
		}finally {
			/*
			 * em todos os casos o EntytiManager é fechado 
			 */
			JpaUtil.closeEntityManager();
		}
		
		
	}
	
	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
