/**
 * projeto-web : 14 de ago de 2017 
 */
package com.flavio.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.flavio.model.Fornecedor;
import com.flavio.repository.FornecedorRepository;
import com.flavio.util.cdi.CDIServiceLocator;

/**
 * @author flavio
 *
 */
@FacesConverter(forClass=Fornecedor.class,value="fornecedorConvert")
public class FornecedorConvert implements Converter{
	
	private FornecedorRepository fornecedorRepository;
	
	public void injecao(){
		this.fornecedorRepository = CDIServiceLocator.getBean(FornecedorRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		System.out.println("conveter fornecedor getAsObject...");
		
		this.injecao();
		
		if(value != null){
			Fornecedor fornecedorConvert = fornecedorRepository.BuscarPorID(new Long(value));
			return fornecedorConvert;
		}
				
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		System.out.println("conveter fornecedor getAsString...");

		Fornecedor fornecedorValue = (Fornecedor) value;
		
		return fornecedorValue.getId() != null ? String.valueOf(fornecedorValue.getId()) : null;
	
	}

}
