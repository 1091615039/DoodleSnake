package com.tianmaying.snake;

public enum Direction {
	UP(0),
	RIGHT(1),
	DOWN(2),
	LEFT(3);
	
	private final int directionCode;
	
	public int directionCode() {
		return directionCode;
	}
	
	private Direction(int directionCode){
		this.directionCode = directionCode;
	}
	
	public boolean compatibleWith(Direction direction) {
		boolean result = true;
		switch (directionCode) {
		case 0:
			if(direction==DOWN) {
				result = false;
			}
			break;
		case 1:
			if(direction==LEFT) {
				result = false;
			}
			break;
		case 2:
			if(direction==UP) {
				result = false;
			}
			break;
		case 3:
			if(direction==RIGHT) {
				result = false;
			}
			break;
		}
		return result;
	}
}
