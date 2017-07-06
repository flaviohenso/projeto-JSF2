package com.flavio.dao.factoryDao;

import com.flavio.dao.implementDaos.HibernateClienteDao;
import com.flavio.dao.implementDaos.HibernatePedidoDao;
import com.flavio.dao.implementDaos.HibernateProdutoDao;
import com.flavio.dao.implementDaos.HibernateVendedorDao;
import com.flavio.dao.interfaceDao.ClienteDao;
import com.flavio.dao.interfaceDao.PedidoDao;
import com.flavio.dao.interfaceDao.ProdutoDao;
import com.flavio.dao.interfaceDao.VendedorDao;

//Fabrica de HibernateDAOs 

public class HibernateDAOFactory extends DAOFactory {

	@Override
	public VendedorDao getVendedorDAO() {
		return new HibernateVendedorDao();
	}

	@Override
	public ClienteDao getClienteDAO() {
		return new HibernateClienteDao();
	}

	@Override
	public ProdutoDao getProdutoDAO() {
		return new HibernateProdutoDao();
	}

	@Override
	public PedidoDao getPedidoDAO() {
		return new HibernatePedidoDao();
	}
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
