package com.game;

import java.awt.Color;
import java.awt.Graphics;

public class Health {


	public static int health = 100;
	private int greenValue = 255;  //@
	
	private int score = 0; 

	public void tick() {
		health = Game.restrict(health, 0, 100);
		health = Game.restrict(health, 0, 255);
		
		greenValue = health*2;
	}


	public void render(Graphics g) { 
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32 );
		g.setColor(new Color(75, greenValue, 0));
		g.fillRect(15, 15, health*2, 32 );
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32 ); //outline
		
		g.drawString("Score:" + score, 20, 70);
	}	
}
