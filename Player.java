package com.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Player extends GameObject {

	private int  width = 30;
	private int  height = 30;
	private Handler handler;



	Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}



	@Override
	public void tick() {
		x += velX;
		y += velY; 

		x = Game.restrict(x, 0, Game.WIDTH-30);
		y = Game.restrict(y, 0, Game.HEIGHT-60);

		collision();
	}

	@Override
	public void render(Graphics g) {

		//	Graphics2D g2d = (Graphics2D) g;



		if(id == ID.Player ) {
			g.setColor(Color.YELLOW);
			g.fillRect(x, y, width, height);				
		}
				else{
					g.setColor(Color.GREEN);
					g.fillRect(x, y, width, height);				
				}
	}

	@Override
	public Rectangle getIntersection() {

		return new Rectangle(x,y,width,height);
	}


	private void collision() {

		for(GameObject i : handler.obj) {	
			ID id  = i.getId();
			if( id ==  ID.Enemy) {
				if(this.getIntersection().intersects(i.getIntersection()))	{
					Health.health -= 2;
				}
			}
		}
	}


}
