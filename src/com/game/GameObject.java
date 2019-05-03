package com.game;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public abstract class GameObject {

	protected int x, y;
	protected boolean removalFlag = false;	

	GameObject(int x, int y){
		this.x = x;
		this.y = y;	
	}

	
	public abstract void update();

	public abstract void render(Graphics g , int xOffset, int yOffset);
	
	public abstract Rectangle getRectangle(); 
	
	public abstract Image getImage();
	
	public abstract int getImageWidth();
	
	public abstract int getImageHeight();
	
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

	
	public void setRemoval(boolean val) {
		this.removalFlag = val;
	}
	
	public boolean getRemoval() {
		return this.removalFlag;
	}

	
	

}
