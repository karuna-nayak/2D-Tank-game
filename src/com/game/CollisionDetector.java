package com.game;

import java.awt.Rectangle;


public class CollisionDetector {	
	private Music music = new Music();

	
	public void detectCollision( GameObject tank, GameObject object2 ) {
		Rectangle r1 = tank.getRectangle();
		
		int speedX = ((Tank)tank).getSpeedX();
		int speedY = ((Tank)tank).getSpeedY();
		//Check for forward and reverse collision
		Rectangle r1Reverse = new Rectangle(tank.getX()- speedX, tank.getY()-speedY , tank.getImageWidth(), tank.getImageHeight());
		Rectangle r1Forward = new Rectangle(tank.getX()+ speedX, tank.getY()+speedY , tank.getImageWidth(), tank.getImageHeight());
		Rectangle r2 = object2.getRectangle();
		
		if(object2 instanceof Tank) {
			
			if(r1Forward.intersects(r2)) {
				((Tank)tank).setForwardCollision(true);
			} else if (r1Reverse.intersects(r2)) {
				((Tank)tank).setReverseCollision(true);
			}
			//check for missile collision of the tank
			for(int k = 0; k<((Tank) tank).getMissileList().size(); k++) {
				GameObject missileObj = ((Tank) tank).getMissileList().get(k);
				Rectangle missileRect = missileObj.getRectangle();
				if(r2.intersects(missileRect)) {
					music.explosion();
					missileObj.setRemoval(true);
					
					if(((Tank) object2).getShieldPower()) {
						if(missileObj instanceof Rocket) {
							((Tank) tank).incScore(20);
							((Tank) object2).decHealth(5);
						}
					} else {
						
						if(missileObj instanceof Shell) {						 
							((Tank) tank).incScore(10);
							((Tank) object2).decHealth(5);	
						} else if(missileObj instanceof Rocket) {
							((Tank) tank).incScore(20);
							((Tank) object2).decHealth(15);
						}
					}														
				}
			}
		}
		else if (object2 instanceof Wall) {
			
			if(r1Forward.intersects(r2)) {
				((Tank)tank).setForwardCollision(true);
			} else if (r1Reverse.intersects(r2)) {
				((Tank)tank).setReverseCollision(true);
			}
			for(int k = 0; k<((Tank) tank).getMissileList().size(); k++) {
				GameObject missileObj = ((Tank) tank).getMissileList().get(k);
				Rectangle missileRect = missileObj.getRectangle();
				if(r2.intersects(missileRect)) {
					music.explosion();
					missileObj.setRemoval(true);
					object2.setRemoval(true);
					((Tank) tank).incScore(5);					

				}
			}			
		}
		else if (object2 instanceof UnbreakableWall) {
			if(r1Forward.intersects(r2)) {
				((Tank)tank).setForwardCollision(true);
			} else if (r1Reverse.intersects(r2)) {
				((Tank)tank).setReverseCollision(true);
			}
			for(int k = 0; k<((Tank) tank).getMissileList().size(); k++) {
				GameObject missileObj = ((Tank) tank).getMissileList().get(k);
				Rectangle missileRect = missileObj.getRectangle();
				if(r2.intersects(missileRect)) {
					music.explosion();
					missileObj.setRemoval(true);					
				}
			}			
		}		
		else if (object2 instanceof Power && ((Power) object2).getPowerType() == PowerType.PowerHealth) {
			
			if(r1.intersects(r2)) {	
				((Tank) tank).setHealthPower(true);				
				object2.setRemoval(true);
			}
		}
		else if (object2 instanceof Power && ((Power) object2).getPowerType() == PowerType.PowerShield) {
			
			if(r1.intersects(r2)) {				
				((Tank) tank).setShieldPower(true);
				((Tank) tank).setPoweredTimeLeft(1000);
				object2.setRemoval(true);
			}
		}	
		else if (object2 instanceof Power && ((Power) object2).getPowerType() == PowerType.PowerRocket) {
		
			if(r1.intersects(r2)) {
				((Tank) tank).setRocketPower(true);
				((Tank) tank).setPoweredTimeLeft(1000);
				object2.setRemoval(true);
			}
 		}	
	}



}
