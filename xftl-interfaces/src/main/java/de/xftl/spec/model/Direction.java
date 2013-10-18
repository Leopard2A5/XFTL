package de.xftl.spec.model;

public enum Direction {
	NORTH,
	EAST,
	SOUTH,
	WEST;
	
	public Direction getOpposite() {
	    switch (this) {
	        case NORTH:
	            return SOUTH;
	        case EAST:
	            return WEST;
	        case SOUTH:
	            return NORTH;
	        case WEST:
	            return EAST;
	        default:
	            throw new IllegalArgumentException("Unknown direction " + this);
	    }
	}
}
