package com.game;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Map extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private GameController controller;
	private Dimension dimension = new Dimension((int) (600),(int) (400));
	private BufferedImage bkImage ;
	
	public Map(GameController controller) {
		this.controller = controller;		
		this.setSize(dimension);
		this.bkImage = LoadImage.load("/Background.bmp");	
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(bkImage, 0, 0, 600, 400, null);
		for(int i = 0; i < controller.getObj().size() ; i++) {
			GameObject object = controller.getObj().get(i);
			g.drawImage(object.getImage(), (int)(object.getX()/3.2), (int)(object.getY()/3.2), 10, 10, null);			
		}
	}

}
