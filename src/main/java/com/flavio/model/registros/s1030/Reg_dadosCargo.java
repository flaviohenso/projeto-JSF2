/**
 * projeto-web : 3 de nov de 2017 
 */
package com.flavio.model.registros.s1030;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author flavio
 *
 */
@XStreamAlias("dadosCargo")
public class Reg_dadosCargo {
	
	private String nmCargo;
	private String codCBO;
	private Reg_cargoPublico cargoPublico;
}
