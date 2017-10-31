/**
 * projeto-web : 31 de out de 2017 
 */
package com.flavio.model.registros.s1020;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author flavio
 *
 */
@XStreamAlias("infoLotacao")
public class Reg_infoLotacao {
	private Reg_inclusao inclusao;
	private Reg_alteracao alteracao;
	private Reg_exclusao exclusao;
}
