/**
 * projeto-web : 10 de out de 2017 
 */
package com.flavio.model.registros.s1000;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author flavio
 *
 */
@XStreamAlias("contato")
public class Reg_contato {
	private String nmCtt;
	private String cpfCtt;
	private String foneFixo;
	private String foneCel;
	private String email;
}
