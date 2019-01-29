package com.game;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

	protected int x, y;
	protected ID id;
	protected int velX, velY;

	GameObject(int x, int y, ID id){
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public abstract void tick();

	public abstract void render(Graphics g);
	
	public abstract Rectangle getIntersection();
	
	
	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ID getId() {
		return this.id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public int getVelX() {
		return this.velX;
	}

	public int getVelY() {
		return this.velY;
	}

	public void setVelX(int vel) {
		this.velX = vel;
	}

	public void setVelY(int vel) {
		this.velY = vel;
	}

}
