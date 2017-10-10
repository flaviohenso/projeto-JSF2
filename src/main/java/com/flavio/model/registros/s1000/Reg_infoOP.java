/**
 * projeto-web : 10 de out de 2017 
 */
package com.flavio.model.registros.s1000;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author flavio
 *
 */
@XStreamAlias("infoOP")
public class Reg_infoOP {
	
	private String nrSiafi;
	
	private Reg_infoEFR infoEFR;
	private Reg_infoEnte infoEnte;
}
