package com.game;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;


public abstract class Tank extends GameObject{

	private int speed;
	private int speedX;
	private int speedY;
	private int angle;
	private String tankId;
	private boolean healthPowered = false;
	private boolean shieldPowered = false;
	private boolean rocketPowered = false;
	private boolean isForwardCollided = false;
	private boolean isReverseCollided = false;
	private int poweredTimeLeft = 0;	
	private PlayerStatus status = new PlayerStatus();		

	private static Image image = LoadImage.load("/Tank1.gif");
	private static int  imageWidth = image.getWidth(null);
	private static int  imageHeight = image.getHeight(null);
	private long lastPressed = System.currentTimeMillis();
	private ArrayList<Missile> missileList;

	
	public String getTankId() {
		return tankId;
	}

	//setting tank id for determining the type of tank, used for collision
	Tank(int x, int y, String tankId){
		super(x, y ); 
		this.tankId = tankId;
		this.speed = 3;
		this.missileList = new ArrayList<>();		
	}

		
	@Override
	public void render(Graphics g, int xOffset, int yOffset) {
		AffineTransform at = AffineTransform.getTranslateInstance(this.getX()-xOffset, this.getY()-yOffset);
		at.rotate(Math.toRadians(this.angle), image.getWidth(null)/2, image.getHeight(null)/2);

		Graphics2D g2d = (Graphics2D) g;		
		g2d.drawImage(image,at,null);	
		if(this.getTankId().equals("Player_1")) {
			this.status.render(50, 50, g2d, "Player 1");			
		} else {
			this.status.render(1200, 50, g2d, "Player 2");
		}	
	}
	
	public void missileRender(Graphics g, int x11 , int x12, int y11, int y12, int x21, int x22, int y21, int y22  ) {	
		for(Missile i : missileList) {	
			if(x12 <= i.getX() && i.getX() <= x11 && y12 <= i.getY() && i.getY() <= y11) {				
				i.render(g,x12,y12);						
			}					
			if(x22 <= i.getX() && i.getX() <= x21 && y22 <= i.getY() && i.getY() <= y21) {			
				i.render(g,x22-TankGame.Width/2,y22);				
			}			
		}
	}

	@Override
	public Rectangle getRectangle() {
		return new Rectangle(this.x,this.y,Tank.imageWidth,Tank.imageHeight); 
		
	}
	
	@Override
	public Image getImage() {		
		return Tank.image;
	}

	protected void move(Direction dir) {
		if(dir == Direction.UP) {
			speedX = (int) Math.round(speed * Math.cos(Math.toRadians(this.angle)));
			speedY = (int) Math.round(speed * Math.sin(Math.toRadians(this.angle)));
			
			if(!(this.isForwardCollided)) {	 
				x += speedX;
				y += speedY;			

			}
			
		}else if(dir == Direction.DOWN) {
			speedX = (int) Math.round(speed * Math.cos(Math.toRadians(this.angle)));
			speedY = (int) Math.round(speed * Math.sin(Math.toRadians(this.angle)));
			
			if(!(this.isReverseCollided)) {
				x -= speedX;
				y -= speedY;
		
			}
		}
		
		
	}
	
	public double getAngle() {		
		return this.angle;
	}
	
	public void setAngle(int val) {
		this.angle += val;
	}

	public void fire(String val) {		
		long now = System.currentTimeMillis();
		long duration = now - lastPressed;
		if (duration > 200) {
			int xVal = this.getX()+(this.getImageWidth()/2);
			int yVal = this.getY()+(this.getImageHeight()/2);
			if(val.equals("Shell")){
				missileList.add(new Shell(xVal, yVal, getAngle()));		
				lastPressed = System.currentTimeMillis();
			} else if(val.equals("Rocket")) {
				missileList.add(new Rocket(xVal, yVal, getAngle()));		
				lastPressed = System.currentTimeMillis();
			}							
		}
	}
	

	@Override
	public int getImageWidth() {		
		return Tank.imageWidth;
	}

	@Override
	public int getImageHeight() {
		return Tank.imageHeight;
	}
	
	public ArrayList<Missile> getMissileList(){
		return this.missileList;
	}
	
	
	public void incScore(int val) {
		this.status.incScore(val);	
	}
	
	public void decHealth(int val) {
		this.status.decHealth(val);		
	}
	
	public void setHealthPower(boolean val) {
		this.healthPowered = val;
	}
	public void setShieldPower(boolean val) {
		this.shieldPowered = val;
	}
	public void setRocketPower(boolean val) {
		this.rocketPowered = val;
	}
	public boolean getHealthPower() {
		return this.healthPowered;
	}
	public boolean getShieldPower() {
		return this.shieldPowered;
	}
	public boolean getRocketPower() {
		return this.rocketPowered;
	}

	public void setPoweredTimeLeft(int val) {
		this.poweredTimeLeft = val;
		
	}
	public int getPoweredTimeLeft() {
		return this.poweredTimeLeft;
		
	}
	

	public int getSpeedX() {
		return this.speedX;
	}
	
	public int getSpeedY() {
		return this.speedY;
	}
	public int getScore() {
		return this.status.getScore();
	}	


	public int getLife() {
		return this.status.getLife();
	}

	public void setForwardCollision(boolean val) {
		this.isForwardCollided = val;
	}
	
	public void setReverseCollision(boolean val) {
		this.isReverseCollided = val;

	}

}
