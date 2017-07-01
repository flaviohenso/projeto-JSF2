package com.flavio.dao;

//classe fabrica de DAOs

public abstract class DAOFactory {
	@SuppressWarnings("rawtypes")
	private static final Class FACTORY_CLASS = HibernateDAOFactory.class;

	public static DAOFactory getFactory() {

		try {

			return (DAOFactory) FACTORY_CLASS.newInstance();

		} catch (InstantiationException e) {

			throw new RuntimeException();

		} catch (IllegalAccessException e) {

			throw new RuntimeException();

		}

	}

	public abstract VendedorDao getVendedorDAO();
	//
	// public abstract UsuarioDao getUsuarioDao();
	//
	// public abstract AutorizacaoDao getAutorizacaoDao();
	//
	// public abstract PermissaoDao getPermissaoDAO();
	//
	// public abstract FtpDao getFtpDAO();
}
