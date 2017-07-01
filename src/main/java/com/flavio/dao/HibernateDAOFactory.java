package com.flavio.dao;

//Fabrica de HibernateDAOs 

public class HibernateDAOFactory extends DAOFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.vendas.dao.DAOFactory#getVendedorDAO()
	 */
	@Override
	public VendedorDao getVendedorDAO() {
		return new HibernateVendedorDao();
	}

	// @Override
	// public LivroDao getLivroDAO() {
	// // TODO Auto-generated method stub
	// return new HibernateLivroDAO();
	// }
	//
	// @Override
	// public UserDao getUserDAO() {
	// // TODO Auto-generated method stub
	// return new HibernateUserDAO();
	// }
	//
	// @Override
	// public UsuarioDao getUsuarioDao() {
	// // TODO Auto-generated method stub
	// return new HibernateUsuarioDAO();
	// }
	//
	// @Override
	// public AutorizacaoDao getAutorizacaoDao() {
	// // TODO Auto-generated method stub
	// return new HibernateAutorizacaoDAO();
	// }
	//
	// @Override
	// public PermissaoDao getPermissaoDAO() {
	// // TODO Auto-generated method stub
	// return new HibernatePermissaoDAO();
	// }
	//
	// @Override
	// public FtpDao getFtpDAO() {
	// // TODO Auto-generated method stub
	// return new HibernateFtpDAO();
	// }

}
