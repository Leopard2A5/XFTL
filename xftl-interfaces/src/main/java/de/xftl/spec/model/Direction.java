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
	
	public static Direction getDirection(Point<Integer> src, Point<Integer> dest) {
		if (src.equals(dest))
			throw new RuntimeException(String.format("%s and %s are equal!", src, dest));
		
		if (src.getX().equals(dest.getX())) {
			return src.getY().compareTo(dest.getY()) > 0 ? NORTH : SOUTH;
		}
		else if (src.getY().equals(dest.getY())) {
			return src.getX().compareTo(dest.getX()) > 0 ? WEST : EAST;
		}
		else {
			throw new RuntimeException(String.format("%s cannot be reached by %s by a purely horizontal or vertical direction!", dest, src));
		}
	}
}
