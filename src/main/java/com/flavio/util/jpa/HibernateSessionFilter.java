package com.flavio.util.jpa;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 
 * NÂO ESTA SENDO UTILIZADA NO PROJETO
 *
 * @author flavio
 * Implementação do OPensessionView
 */

//@WebFilter(urlPatterns="/*") essa anotação deve ser descomentada caso seja necessário ultilizar opensessionview
public class HibernateSessionFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		/*
		 *  inicia a transação antes de processar o request
		 */
//		EntityManager em = EntityManagerProducer.getConnection();
//		try {
//			
//			/*
//			 * Inicia a transaction
//			 */
//			EntityManagerProducer.beginTransaction(em);
//			
//			/*
//			 * Seta o um atributo no request (esse atributo é o entityManager), para ser obtido posteriormente,
//			 * ondem precisar
//			 */
//			request.setAttribute("entityManager", em);
//			
//			chain.doFilter(request, response);
//			
//			/*
//			 * Comita a transaction
//			 */
//			EntityManagerProducer.commitTransaction(em);
//		} catch (Exception e) {
//			
//			/*
//			 * Verifica em caso de exception se o EntityManager esta aberta, estando aberta chama o metodo de RollBack
//			 */
//			if (em.isOpen()) {
//				System.out.println("RollBack: <<<<<<<<<<<");
//				EntityManagerProducer.rollBackTransaction(em);
//			}
//		}finally {
//			/*
//			 * em todos os casos o EntytiManager é fechado 
//			 */
//			EntityManagerProducer.closeEntityManager();
//		}
//		
//		
	}
	
	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
