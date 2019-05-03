package com.game;

import java.util.Random;

public class PowerUpGenerator implements Runnable {
	
	
	private Random randTime = new Random();
	private int sleepTime ;
	private Random powerUpIndex = new Random();
	private int powerUpType;
	private boolean healthPowerUp = false;
	private boolean shieldPowerUp = false;
	private boolean rocketPowerUp = false;
	
	
	
	@Override
	public void run() {
		while(true) {
			sleepTime = randTime.nextInt(21) + 20;
			
			try {
				Thread.sleep(sleepTime*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			powerUpType = powerUpIndex.nextInt(3);
			
			if(powerUpType == 0) {
				healthPowerUp = true;
			} else if(powerUpType == 1) {
				shieldPowerUp = true;
			} else if (powerUpType == 2) {
				rocketPowerUp = true;
			}
		}		
	}
	
	
	public boolean getHealthPower() {
		return healthPowerUp;
	}
	
	public void setHealthPower(boolean val) {
		 this.healthPowerUp = val ;
	}
	
	public boolean getShieldPower() {
		return shieldPowerUp;
	}
	
	public void setShieldPower(boolean val) {
		 this.shieldPowerUp = val ;
	}
	
	public boolean getRocketPower() {
		return rocketPowerUp;
	}
	
	public void setRocketPower(boolean val) {
		 this.rocketPowerUp = val ;
	}
	

}
