package com.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class PlayerStatus {

	private int health;  
	private int score = 0; 
	private int life;

	public PlayerStatus() {	
		this.health = 100;
		this.life = 3;
	}


	public void render(int x, int y, Graphics g, String player) { 					
		Font font = new Font("Sans serif", 0, 30);
		g.setColor(Color.gray);
		g.fillRect(x, y, 200, 32 );
		if(this.health<0 || this.health>100) {
			health = 100;
		}
		g.setColor(new Color(75, this.health*2, 0));		
		g.fillRect(x, y, this.health*2, 32 );
		g.setColor(Color.white);
		g.drawRect(x, y, 200, 32 ); //outline

		g.setFont(font);
		g.setColor(Color.CYAN);
		g.drawString("Score:" + score, x, y+60);		
		g.drawString("Life:" + life, x, y+85);	
		g.drawString( player, x, y+120);	
	}	


	public void decHealth(int val) {	
		this.health -= val;	
		if(this.health < 0 && this.life>0) {				
			this.health = 100;	
			this.life--;
			if(this.life==0) {				
				TankGame.setGameOver(true);
				System.out.println("gameover");
			}
		}
	}

	public void incScore(int val) {
		this.score += val;
		if(this.score < 0) {
			this.score = 0;
		}
	}


	public int getScore() {
		return this.score;
	}
	
	public int getLife() {
		return this.life;
	}

}



