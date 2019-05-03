package com.game;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;


public class UnbreakableWall extends GameObject {

	private static final Image image = LoadImage.load("/Wall2.gif");	

	private static int imageHeight = image.getHeight(null);
	private static int imageWidth = image.getWidth(null);
	


	UnbreakableWall(int x, int y) {
		super(x, y); 
	}	
	
	@Override
	public void update() {			
	}

	@Override
	public void render(Graphics g, int xOffset, int yOffset) {	
		g.drawImage(image, this.x - xOffset,this.y-yOffset, null);		
	}

	
	@Override
	public Rectangle getRectangle() {
		return new Rectangle(this.x,this.y,UnbreakableWall.imageWidth,UnbreakableWall.imageHeight);
	}


	@Override
	public Image getImage() {		
		return UnbreakableWall.image;
	}
	@Override
	public int getImageWidth() {		
		return UnbreakableWall.imageWidth;
	}


	@Override
	public int getImageHeight() {
		return UnbreakableWall.imageHeight;
	}


}
