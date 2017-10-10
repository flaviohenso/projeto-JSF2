/**
 * projeto-web : 9 de out de 2017 
 */
package com.flavio.service;

import java.io.Serializable;

import com.thoughtworks.xstream.XStream;

/**
 * @author flavio
 *
 */
public abstract class GeraXMLGeneric<T, Type extends Serializable> {


	private XStream xStream = new XStream();
	
	/**
	 * Alias Xstream
	 **/
	/**
	 * 
	 */
	public GeraXMLGeneric(String nome, T type) {
		xStream.alias(nome, type.getClass());
	}
	
	
	public String geraXMLObject(T type, String nome){
		return xStream.toXML(type);
	}
	
}
