/**
 * projeto-web : 31 de out de 2017 
 */
package com.flavio.model.registros.s1020;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author flavio
 *
 */
@XStreamAlias("fpasLotacao")
public class Reg_fpasLotacao {
	private String fpas;
	private String codTercs;
	private String codTercsSusp;
	private Reg_infoProcJudTerceiros infoProcJudTerceiros;
}
