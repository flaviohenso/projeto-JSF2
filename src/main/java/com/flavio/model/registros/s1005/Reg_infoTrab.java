/**
 * projeto-web : 23 de out de 2017 
 */
package com.flavio.model.registros.s1005;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author flavio
 *
 */
@XStreamAlias("infoTrab")
public class Reg_infoTrab {
	private String regPt;
	private Reg_infoApr infoApr;
	private Reg_infoPCD infoPCD;
	private Reg_alteracao alteracao;
}
