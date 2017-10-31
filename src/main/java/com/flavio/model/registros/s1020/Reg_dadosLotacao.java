/**
 * projeto-web : 31 de out de 2017 
 */
package com.flavio.model.registros.s1020;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author flavio
 *
 */
@XStreamAlias("dadosLotacao")
public class Reg_dadosLotacao {
	
	private String tpLotacao;
	private String tpInsc;
	private String nrInsc;
	private Reg_fpasLotacao fpasLotacao;
	private Reg_infoEmprParcial infoEmprParcial;
}
