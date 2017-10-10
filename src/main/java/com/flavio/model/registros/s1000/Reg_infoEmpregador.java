/**
 * projeto-web : 10 de out de 2017 
 */
package com.flavio.model.registros.s1000;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author flavio
 *
 */
@XStreamAlias("infoEmpregador")
public class Reg_infoEmpregador {
	
	private Reg_inclusao inclusao;
	private Reg_alteracao alteracao;
	private Reg_exclusao exclusao;
}
