package com.game;

import java.awt.event.KeyAdapter;

import java.awt.event.KeyEvent;

public class KeyControl extends KeyAdapter {

	private Handler handler;

	public KeyControl(Handler handler) {
		this.handler = handler;	
	}


	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		//System.out.println(key);
		
		for(int i = 0; i< handler.obj.size(); i++) {
			
			GameObject obj = handler.obj.get(i);

			if(obj.getId() == ID.Player) {
				if(key == KeyEvent.VK_UP) {
					obj.setVelY(-2);
				}
				if(key == KeyEvent.VK_DOWN) {
					obj.setVelY(2);
				}
				
				if (key == KeyEvent.VK_LEFT) {
					obj.setVelX(-2);
		        }
		        if (key == KeyEvent.VK_RIGHT) {
		        	obj.setVelX(2);
		        }		

			}
			
			if(obj.getId() == ID.Player2) {
				if(key == KeyEvent.VK_W) {
					obj.setVelY(-2);
				}
				if(key == KeyEvent.VK_S) {
					obj.setVelY(2);
				}
				
				if (key == KeyEvent.VK_A) {
					obj.setVelX(-2);
		        }
		        if (key == KeyEvent.VK_D) {
		        	obj.setVelX(2);
		        }		

			}
			
			

		}	

	}

		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			for(int i = 0; i< handler.obj.size(); i++) {
				
				GameObject obj = handler.obj.get(i);

				if(obj.getId() == ID.Player) {
					if(key == KeyEvent.VK_UP) {
						obj.setVelY(0);
					}
					if(key == KeyEvent.VK_DOWN) {
						obj.setVelY(0);
					}
					
					if (key == KeyEvent.VK_LEFT) {
						obj.setVelX(0);
			        }
			        if (key == KeyEvent.VK_RIGHT) {
			        	obj.setVelX(0);
			        }		

				}
				
				if(obj.getId() == ID.Player2) {
					if(key == KeyEvent.VK_W) {
						obj.setVelY(0);
					}
					if(key == KeyEvent.VK_S) {
						obj.setVelY(0);
					}
					
					if (key == KeyEvent.VK_A) {
						obj.setVelX(0);
			        }
			        if (key == KeyEvent.VK_D) {
			        	obj.setVelX(0);
			        }		

				}

			}
			
			
			
			
			}


}
