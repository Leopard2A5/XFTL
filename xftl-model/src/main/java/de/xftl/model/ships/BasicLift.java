package de.xftl.model.ships;

import de.xftl.spec.model.ships.Lift;

public class BasicLift extends AbstractRoomConnector implements Lift {

    private boolean _open;
    
	public BasicLift() {
		super();
	}
	
	@Override
	public void update(float elapsedTime) {
		// TODO Auto-generated method stub

	}
	
	@Override
    public boolean isOpen() {
        return _open;
    }

    @Override
    public void close() {
        _open = false;
    }

    @Override
    public void open() {
        _open = true;
    }
	
}
