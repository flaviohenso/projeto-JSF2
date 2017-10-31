/**
 * projeto-web : 23 de out de 2017 
 */
package com.flavio.model.registros.s1005;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author flavio
 *
 */
@XStreamAlias("dadosEstab")
public class Reg_dadosEstab {
	private String cnaePrep;
	private Reg_aliqGilrat aliqGilrat;
	private Reg_infoCaepf infoCaepf;
	private Reg_infoObra infoObra;
	private Reg_infoTrab infoTrab;
}
