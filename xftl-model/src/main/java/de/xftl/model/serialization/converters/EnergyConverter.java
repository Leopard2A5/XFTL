package de.xftl.model.serialization.converters;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import de.xftl.spec.model.systems.Energy;

public class EnergyConverter implements Converter {

	private static final String VALUE = "value";

	@Override
	public boolean canConvert(@SuppressWarnings("rawtypes") Class clazz) {
		return clazz.equals(Energy.class);
	}

	@Override
	public void marshal(Object value, HierarchicalStreamWriter w, MarshallingContext ctx) {
		Energy e = (Energy) value;
		
		w.addAttribute(VALUE, Integer.toString(e.intValue()));
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader r, UnmarshallingContext ctx) {
		Integer value = Integer.valueOf(r.getAttribute(VALUE));
		
		return Energy.valueOf(value.intValue());
	}

}
