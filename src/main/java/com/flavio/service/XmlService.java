/**
 * projeto-web : 9 de out de 2017 
 */
package com.flavio.service;

import javax.enterprise.context.RequestScoped;

import com.flavio.model.Categoria;
import com.flavio.model.Produto;
import com.thoughtworks.xstream.XStream;

/**
 * @author flavio
 *
 */
@RequestScoped
public class XmlService{
	
	private XStream xStream = new XStream();
	
	private void processarAnotacoes(){
		xStream.processAnnotations(Produto.class);
		xStream.processAnnotations(Categoria.class);
	}
		
	public String objectTOxmlProduto(Produto produto){
		this.processarAnotacoes();
		return xStream.toXML(produto);
	}
	
	public Produto xmlToObjectProduto(String xml){
		this.processarAnotacoes();
		return (Produto) xStream.fromXML(xml, Produto.class);
	}
	
}
