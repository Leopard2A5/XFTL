package de.xftl.model.serialization;

import java.io.OutputStream;

import com.thoughtworks.xstream.XStream;

import de.xftl.model.serialization.converters.EnergyConverter;
import de.xftl.spec.model.systems.Energy;

public class XFTLModelWriter {

	public void write(final Object o, final OutputStream dest) {
		buildXStream().toXML(o, dest);
	}
	
	private XStream buildXStream() {
		final XStream xs = new XStream();
		
		xs.alias("energy", Energy.class);
		
		xs.registerConverter(new EnergyConverter());
		
		return xs;
	}
	
}
