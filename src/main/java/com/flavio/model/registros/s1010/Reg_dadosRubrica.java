/**
 * projeto-web : 31 de out de 2017 
 */
package com.flavio.model.registros.s1010;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author flavio
 *
 */
@XStreamAlias("dadosRubrica")
public class Reg_dadosRubrica {
	
private String dscRubr;
private String natRubr;
private String tpRubr;
private String codIncCP;
private String codIncIRRF;
private String codIncFGTS;
private String codIncSIND;
private String repDSR;
private String rep13;
private String repFerias;
private String repAviso;
private String observacao;
private Reg_ideProcessoCP ideProcessoCP;
private Reg_ideProcessoIRRF ideProcessoIRRF;
private Reg_ideProcessoFGTS ideProcessoFGTS;
private Reg_ideProcessoSIND ideProcessoSIND;
}
