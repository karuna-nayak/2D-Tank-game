package com.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Wall extends GameObject {
	
	private static final Image image = LoadImage.load("/Wall1.gif");	
	private static int imageHeight = image.getHeight(null);
	private static int imageWidth = image.getWidth(null);
	
	Wall(int x, int y) {
		super(x, y); 
	}
	
	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g, int xOffset, int yOffset) {
		g.drawImage(image,this.x - xOffset, this.y - yOffset, null);
	}
	
	

	@Override
	public Rectangle getRectangle() {
		return new Rectangle(this.x,this.y,Wall.imageWidth,Wall.imageHeight);
	}
	
	@Override
	public Image getImage() {		
		return Wall.image;
	}


	@Override
	public int getImageWidth() {		
		return Wall.imageWidth;
	}


	@Override
	public int getImageHeight() {
		return Wall.imageHeight;
	}
	
	

}