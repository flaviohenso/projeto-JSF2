package com.flavio.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.flavio.model.Produto;
import com.flavio.repository.ProdutoRepository;
import com.flavio.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Produto.class)
public class ProdutoConvert implements Converter {
	

	private ProdutoRepository produtoRepository;
	
	public void injecao(){
		this.produtoRepository = CDIServiceLocator.getBean(ProdutoRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		this.injecao();
		
		if(value != null){
			Produto produtoConvert = produtoRepository.BuscarPorID(new Long(value));
			return produtoConvert;
		}
				
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		Produto produtoValue = (Produto) value;
		
		return produtoValue.getId() != null ? String.valueOf(produtoValue.getId()) : null;
	}

}
