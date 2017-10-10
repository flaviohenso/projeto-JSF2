/**
 * projeto-web : 10 de out de 2017 
 */
package com.flavio.xstream.convert;

import com.flavio.model.Produto;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * @author flavio
 *
 */
public class ProdutoConvert implements Converter{

	@Override
	public boolean canConvert(Class type) {
		return type.equals(Produto.class);
	}

	@Override
	public void marshal(Object object, HierarchicalStreamWriter writer, MarshallingContext context) {
        writer.setValue(String.valueOf(object));
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		Long id = new Long(reader.getValue());
        return id;
	}

}
