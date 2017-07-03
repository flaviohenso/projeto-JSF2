/**Autor: Flávio Henrique
 * 9 de abr de 2017ProjetoBootStrap2
 */
package com.flavio.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
/*
 * @DiscriminatorValue contem o valor que irá identificar cada classe na tabela do
 * banco de dados
 */
@DiscriminatorValue("vendedor")
public class Vendedor extends Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;

}
