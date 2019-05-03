package com.game;

public abstract class Missile extends GameObject{
	
	private final int speed = 10;
	private int speedX;
	private int speedY;


	Missile(int x, int y, double angle){ 
		super(x-6,y-6); 
		this.speedX = (int) (speed * Math.cos(Math.toRadians(angle)));
		this.speedY = (int) (speed * Math.sin(Math.toRadians(angle)));
	}

	@Override
	public void update() {
		if(this.getRemoval()) {
			
		} else {
			x += speedX;
			y += speedY;	
		}		
	}
		
}