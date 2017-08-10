/**
 * projeto-web : 8 de ago de 2017 
 */
package com.flavio.service;

import java.util.List;

import org.primefaces.model.LazyDataModel;

import com.flavio.model.Pedido;
import com.flavio.util.Paginacao;

/**
 * @author flavio
 *
 */
public class PedidoService implements GenericService<Pedido> {

	/* (non-Javadoc)
	 * @see com.flavio.service.GenericService#listRepository()
	 */
	@Override
	public List<Pedido> listRepository() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.flavio.service.GenericService#salvar(java.lang.Object)
	 */
	@Override
	public boolean salvar(Pedido t) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.flavio.service.GenericService#remover(java.lang.Object)
	 */
	@Override
	public boolean remover(Pedido t) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.flavio.service.GenericService#consultaPaginada(com.flavio.util.Paginacao)
	 */
	@Override
	public LazyDataModel<Pedido> consultaPaginada(Paginacao paginacao) {
		// TODO Auto-generated method stub
		return null;
	}

}
