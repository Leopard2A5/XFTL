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
	public boolean canConvert(@SuppressWarnings("rawtypes") final Class clazz) {
		return clazz.equals(Energy.class);
	}

	@Override
	public void marshal(final Object value, final HierarchicalStreamWriter w, final MarshallingContext ctx) {
		final Energy e = (Energy) value;
		
		w.addAttribute(VALUE, Integer.toString(e.intValue()));
	}

	@Override
	public Object unmarshal(final HierarchicalStreamReader r, final UnmarshallingContext ctx) {
		final Integer value = Integer.valueOf(r.getAttribute(VALUE));
		
		return Energy.valueOf(value.intValue());
	}

}
