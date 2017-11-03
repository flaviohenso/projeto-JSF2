/**
 * projeto-web : 3 de nov de 2017 
 */
package com.flavio.model.registros.s1035;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author flavio
 *
 */
@XStreamAlias("evtTabCarreira")
public class Reg_evtTabCarreira {
	private String id;
	private Reg_ideEvento ideEvento;
	private Reg_ideEmpregador ideEmpregador;
	private Reg_infoCarreira infoCarreira;
}
