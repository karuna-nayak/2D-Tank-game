package com.game;
/**
 * loop through all the game objects, update and render them.
 * @author vaishalibisht
 *
 */

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

public class Handler {
	
	ArrayList<GameObject> obj = new ArrayList<>();
	
	public void tick() {
		for(GameObject i : obj) {
			i.tick();
		}
	}
	
	public void render(Graphics g) {
		//System.out.println("in render");
		//System.out.println("length"+ obj.size());	
		for(GameObject i : obj) {	
			i.render(g);
			//System.out.println(i);
		}			
	}
	
	
	public void addObject(GameObject object) {
		obj.add(object);	
	}
	
	
	public void removeObject(GameObject object) {
		obj.remove(object); 	
	}
	
}
