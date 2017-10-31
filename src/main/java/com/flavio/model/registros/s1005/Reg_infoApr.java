/**
 * projeto-web : 23 de out de 2017 
 */
package com.flavio.model.registros.s1005;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author flavio
 *
 */
@XStreamAlias("infoApr")
public class Reg_infoApr {
	
	private String contApr; 
	private String nrProcJud;
	private String contEntEd;
	private Reg_infoEntEduc infoEntEduc;
}
