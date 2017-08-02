package com.flavio.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.flavio.model.Categoria;
import com.flavio.repository.CategoriaRepository;
import com.flavio.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Categoria.class)
public class CategoriaConvert implements Converter {
	

	private CategoriaRepository categoriaRepository;
	
	public void injecao(){
		this.categoriaRepository = CDIServiceLocator.getBean(CategoriaRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		this.injecao();
		
		if(value != null){
			Categoria categoriaConvert = categoriaRepository.BuscarPorID(new Long(value));
			return categoriaConvert;
		}
				
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		Categoria categoriaValue = (Categoria) value;
		
		return categoriaValue.getId() != null ? String.valueOf(categoriaValue.getId()) : null;
	}

}
