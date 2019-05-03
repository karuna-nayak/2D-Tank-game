package com.game;

public class PlayerTank extends Tank{


	private boolean keyUp;
	private boolean keyDown;
	private boolean keyRight;
	private boolean keyLeft;
	private boolean keyFire;

	

	

	PlayerTank(int x, int y, String tankId){
		super(x, y, tankId);
	}


	public void update() {		
		if (this.keyUp) {
			this.move(Direction.UP);
		}
		if (this.keyDown) {
			this.move(Direction.DOWN);
		}
		if (this.keyLeft) {
			this.anticlockwiseRotation();
		}
		if (this.keyRight) {
			this.clockwiseRotation();
		}
		if(this.keyFire) {
			if(this.getRocketPower()) {				
				this.fire("Rocket");
				
			} else
				this.fire("Shell");
		}

		
		this.setForwardCollision(false);
		this.setReverseCollision(false);
		
		if(this.getHealthPower()) {
			this.decHealth(-30);
			this.setHealthPower(false);
		}
		 
		if(this.getRocketPower() || this.getShieldPower()) {
			int timeLeft = this.getPoweredTimeLeft();
			if( timeLeft > 0) {				
				this.setPoweredTimeLeft(timeLeft - 1 );
			} else if(timeLeft == 0) {
				this.setRocketPower(false);
				this.setShieldPower(false);
			}
		}
		for(int i = 0; i < this.getMissileList().size(); i++) {	
			if(this.getMissileList().get(i).removalFlag) {
				this.getMissileList().remove(i);
			} else {
				this.getMissileList().get(i).update();
			}
		}		
	}

	private void clockwiseRotation() {
		this.setAngle(3);
	}

	private void anticlockwiseRotation() {		
		this.setAngle(-3);
	}

	public  void setKeyUp(boolean value) {
		this.keyUp = value;
	}

	public  void setKeyDown(boolean value) {
		this.keyDown = value;
	}

	public  void setKeyRight(boolean value) {
		this.keyRight = value;
	}

	public  void setKeyLeft(boolean value)  {
		this.keyLeft = value;
	}	


	public void setKeyFire(boolean value)  {
		this.keyFire = value;
	}
	
}
