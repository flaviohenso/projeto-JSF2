/**
 * projeto-web : 10 de out de 2017 
 */
package com.flavio.model.registros.s1000;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author flavio
 *
 */
@XStreamAlias("infoCadastro")
public class Reg_infoCadastro {
	
	private String nmRazao;
	private String classTrib;
	private String natJurid;
	private String indCoop;
	private String indConstr;
	private String indDesFolha;
	private String indOptRegEletron;
	private String indEntEd;
	private String indEtt;
	private String nrRegEtt;
		
	private Reg_dadosIsencao dadosIsencao;
	private Reg_contato contato;
	private Reg_infoOP infoOP;
	private Reg_infoOrgInternacional infoOrgInternacional;
	private Reg_softwareHouse softwareHouse;
	private Reg_infoComplementares infoComplementares;
}
