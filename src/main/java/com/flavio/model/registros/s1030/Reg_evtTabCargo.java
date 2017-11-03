/**
 * projeto-web : 3 de nov de 2017 
 */
package com.flavio.model.registros.s1030;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author flavio
 *
 */
@XStreamAlias("evtTabCargo")
public class Reg_evtTabCargo {
	
	private String id;
	private Reg_ideEvento ideEvento;
	private Reg_ideEmpregador ideEmpregador;
	private Reg_infoCargo infoCargo;
}
