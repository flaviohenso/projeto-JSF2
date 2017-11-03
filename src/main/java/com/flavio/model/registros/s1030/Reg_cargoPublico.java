/**
 * projeto-web : 3 de nov de 2017 
 */
package com.flavio.model.registros.s1030;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author flavio
 *
 */
@XStreamAlias("cargoPublico")
public class Reg_cargoPublico {
	private String acumCargo;
	private String contagemEsp;
	private String dedicExcl;
	private Reg_leiCargo leiCargo;
}
