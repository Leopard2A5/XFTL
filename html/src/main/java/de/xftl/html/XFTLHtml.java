package de.xftl.html;

import de.xftl.core.XFTL;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class XFTLHtml extends GwtApplication {
	@Override
	public ApplicationListener getApplicationListener () {
		return new XFTL();
	}
	
	@Override
	public GwtApplicationConfiguration getConfig () {
		return new GwtApplicationConfiguration(480, 320);
	}
}
