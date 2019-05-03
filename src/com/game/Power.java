package com.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.HashMap;

public class Power extends GameObject {
	
	private static Image image;
	private static int imageHeight = 64;
	private static int imageWidth = 64;
	private int timeLeft;
	private PowerType powerType;
    

    
    public PowerType getPowerType() {
		return powerType;
	}

	public static HashMap<PowerType, Image> imgMap = new HashMap<>();
    
    static {
    	imgMap.put(PowerType.PowerHealth,LoadImage.load("/HealthBig.png"));
    	imgMap.put(PowerType.PowerRocket, LoadImage.load("/Bolt.png"));
    	imgMap.put(PowerType.PowerShield, LoadImage.load("/Shield1.png"));
    }
	
	Power(int x, int y, PowerType type) {
		super(x,y);
		this.timeLeft = 1500;
		this.powerType = type;
		Power.image = imgMap.get(type);		
	}

	@Override
	public void update() {
		if(this.timeLeft > 0) {
			this.timeLeft -= 1;
		} else if(this.timeLeft == 0)
			this.removalFlag = true;		
	}

	@Override
	public void render(Graphics g, int xOffset, int yOffset) {
		g.drawImage(image,this.x-xOffset ,this.y-yOffset, null);			
	}


	public Rectangle getRectangle() {
		return new Rectangle(this.x,this.y,imageWidth,imageHeight);
	}


	@Override
	public Image getImage() {
		return Power.image;
	}

	@Override
	public int getImageWidth() {
		return Power.imageWidth;
	}

	@Override
	public int getImageHeight() {
		return Power.imageHeight;
	}
		
}
