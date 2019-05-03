package com.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;



public class Shell extends Missile {
	
	private static Image image = LoadImage.load("/Shell.gif");	
	private static int imageHeight = image.getHeight(null);
	private static int imageWidth = image.getWidth(null);
	

	Shell(int x, int y, double angle) {
		super(x, y, angle);
		
	}

	@Override
	public void render(Graphics g, int xOffset, int yOffset) {
		g.drawImage(image,this.x - xOffset,this.y- yOffset, null);	
		
	}

	@Override
	public Rectangle getRectangle() {
		return new Rectangle(this.x,this.y,Shell.imageWidth,Shell.imageHeight);
	}
	
	@Override
	public Image getImage() {		
		return Shell.image;
	}
	
	@Override
	public int getImageWidth() {		
		return Shell.imageWidth;
	}


	@Override
	public int getImageHeight() {
		return Shell.imageHeight;
	}

	

}
