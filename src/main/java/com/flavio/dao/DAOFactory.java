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
	
	public abstract ClienteDao getClienteDAO();

	public abstract ProdutoDao getProdutoDAO();
	
	public abstract PedidoDao getPedidoDAO();
	//
	// public abstract FtpDao getFtpDAO();
}
