package com.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyControl extends KeyAdapter {


	private PlayerTank player;
	private final int keyUp;
    private final int keyDown;
    private final int keyRight;
    private final int keyLeft;
    private final int keyFire;
	
	public KeyControl(PlayerTank player, int keyUp, int keyDown, int keyLeft, int keyRight, int keyFire) {
		this.player = player;
	    this.keyUp = keyUp;
	    this.keyDown = keyDown;
	    this.keyLeft = keyLeft;
	    this.keyRight = keyRight;
	    this.keyFire = keyFire;
	  }



	@Override
	public void keyPressed(KeyEvent e) {		
		int key = e.getKeyCode();

			if(key == keyUp) {
				player.setKeyUp(true);
			}
			else if(key == keyDown ) {
				player.setKeyDown(true);
			}
			else if (key == keyLeft) {
				player.setKeyLeft(true);
			}
			else if (key == keyRight) {
				player.setKeyRight(true);		   
			}	
			else if(key == keyFire) {					
				player.setKeyFire(true);				
			}
	}	



	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

			if(key == keyUp) {
				player.setKeyUp(false);
			}
			else if(key == keyDown) {
				player.setKeyDown(false);
			}
			else if (key == keyLeft) {
				player.setKeyLeft(false);
			}
			else if (key == keyRight) {
				player.setKeyRight(false);
			}
			else if(key == keyFire) {
				player.setKeyFire(false);
			}			

	}

}