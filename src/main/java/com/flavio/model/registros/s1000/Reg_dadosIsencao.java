/**
 * projeto-web : 10 de out de 2017 
 */
package com.flavio.model.registros.s1000;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author flavio
 *
 */
@XStreamAlias("dadosIsencao")
public class Reg_dadosIsencao {
	private String ideMinLei;
	private String nrCertif;
	private String dtEmisCertif;
	private String dtVencCertif;
	private String nrProtRenov;
	private String dtProtRenov;
	private String dtDou;
	private String pagDou;
}
