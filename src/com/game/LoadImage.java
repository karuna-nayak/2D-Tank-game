package com.game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class LoadImage {	

	public static BufferedImage load(String fileName) {	

		try {
			return ImageIO.read(LoadImage.class.getResource("/resources"+ fileName));
		} catch (IOException e) {
			System.out.println("Image Loading failure.");
		}
		return null;
	}
}


