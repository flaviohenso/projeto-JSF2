/**
 * projeto-web : 31 de out de 2017 
 */
package com.flavio.model.registros.s1020;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author flavio
 *
 */
@XStreamAlias("evtTabLotacao")
public class Reg_evtTabLotacao {
	
	private String id;
	private Reg_ideEvento ideEvento;
	private Reg_ideEmpregador ideEmpregador;
	private Reg_infoLotacao infoLotacao;
}
