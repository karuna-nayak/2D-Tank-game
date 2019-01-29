package com.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy extends GameObject {

	 private int  width = 30;
	 private int  height = 30;
	
	
	Enemy(int x, int y, ID id) {
		super(x, y, id);
		velY = 5;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(y<=0 || y >= Game.HEIGHT-21) {
			
			velY *= -1;
		}

	}

	@Override
	public void render(Graphics g) { 	
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height );					
	}

	@Override
	public Rectangle getIntersection() {
		return new Rectangle(x,y,width,height);
		
	}

}
