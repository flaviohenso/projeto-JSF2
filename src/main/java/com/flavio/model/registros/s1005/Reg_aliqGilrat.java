/**
 * projeto-web : 23 de out de 2017 
 */
package com.flavio.model.registros.s1005;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author flavio
 *
 */
@XStreamAlias("aliqGilrat")
public class Reg_aliqGilrat {
	
	private String aliqGilrat;
	private String fap;
	private String aliqRatAjust;
	private Reg_procAdmJudRat procAdmJudRat;
	private Reg_procAdmJudFap procAdmJudFap;
}
