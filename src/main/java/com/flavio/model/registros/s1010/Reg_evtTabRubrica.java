/**
 * projeto-web : 31 de out de 2017 
 */
package com.flavio.model.registros.s1010;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author flavio
 *
 */
@XStreamAlias("evtTabRubrica")
public class Reg_evtTabRubrica {
	
private String id;
private Reg_ideEvento ideEvento;
private Reg_ideEmpregador ideEmpregador;
private Reg_infoRubrica infoRubrica;
}
