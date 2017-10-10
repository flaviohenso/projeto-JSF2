/**
 * projeto-web : 10 de out de 2017 
 */
package com.flavio.model.registros.s1000;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author flavio
 *
 */
@XStreamAlias("evtInfoEmpregador")
public class Reg_evtInfoEmpregador {

	private Reg_ideEvento ideEvento;
	private Reg_ideEmpregador deEmpregador;
	private Reg_infoEmpregador infoEmpregador;
}
