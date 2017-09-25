package com.flavio.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.flavio.model.Usuario;
import com.flavio.util.Paginacao;
import com.flavio.util.jpa.EntityManagerProducer;

public class UsuarioRepository implements GenericRepository<Usuario, Serializable> {

	private static final long serialVersionUID = 1L;

	public static Log log = LogFactory.getLog(UsuarioRepository.class);

	@Inject
	private EntityManager entityManager;

	public List<Usuario> listAll() {
		return entityManager.createNamedQuery("Usuario.findAll", Usuario.class).getResultList();
	}

	public boolean save(Usuario usuario) throws Exception {
		if (entityManager != null) {
			try {
				EntityManagerProducer.beginTransaction(entityManager);
				this.entityManager.persist(usuario);
				return true;
			} catch (Exception e) {
				log.error("Ocorreu um erro ao salvar usuario: " + e.getMessage());
				return false;
			}
		} else {
			return false;
		}
	}

	public Usuario porEmail(String email) {
		try {
			EntityManagerProducer.beginTransaction(entityManager);
			return this.entityManager.createNamedQuery("Usuario.porEmail", Usuario.class)
					.setParameter("email", email.toLowerCase()).getSingleResult();
		} catch (NoResultException e) {
			log.info("Nenhuma usuário encontrado para:"+ email +"!", e);
		}
		return null;
	}

	@Override
	public List<Usuario> filtrados(Paginacao paginacao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int quantidadeFiltrados(Paginacao paginacao) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean remover(Usuario entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Usuario BuscarPorID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
