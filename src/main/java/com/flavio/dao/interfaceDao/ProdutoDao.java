/**Autor: Flávio Henrique
 * 9 de abr de 2017ProjetoBootStrap2
 */
package com.flavio.dao.interfaceDao;

import java.util.List;

import com.flavio.model.Cliente;
import com.flavio.model.Produto;

/**
 * @author root
 *
 */
public interface ProdutoDao extends GenericDAO<Produto, Long> {
	
	public List<Produto> listAllQ();

}
