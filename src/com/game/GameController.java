package com.game;


import java.awt.Graphics;
import java.util.ArrayList;


public class GameController {

	private ArrayList<GameObject> obj;
	private CollisionDetector detector;

	public GameController() {
		obj = new ArrayList<>();
		detector = new CollisionDetector();
	}



	public ArrayList<GameObject> getObj() {
		return obj;
	}



	public void update() {	
		for(int i=0 ; i < obj.size(); i++) {
			GameObject obj1 = obj.get(i);
			if(obj1 instanceof Tank) {
				for(int j = 0; j <obj.size(); j++) {
					if(j != i) {
						GameObject obj2 = obj.get(j);
						detector.detectCollision(obj1,obj2);
					}	
				}
			}	
			obj1.update();
		}
	}

	
	/*
	 * Split screen render
	 * Divide the frame into two parts and calculate the (X,Y) related to Tank1 and Tank2
	 */
	public void render(Graphics g) {	
		int tank1X2,tank1X1,tank2X2,tank2X1;
		int tank1Y2,tank1Y1,tank2Y2,tank2Y1;
		//subtracting 8 from width so that divided frame has 8 pixels black line
		if (obj.get(0).getX() < (TankGame.Width - 8) /4) {
			 tank1X2 = 0;
			 tank1X1 = (TankGame.Width - 8) /2;
		} else if(obj.get(0).getX() > (TankGame.GameWidth - (TankGame.Width - 8) /4) ) {
			 tank1X1 = TankGame.GameWidth;
			 tank1X2 = TankGame.GameWidth - (TankGame.Width - 8)/2;
		} else {
			 tank1X1 = obj.get(0).getX() + (TankGame.Width - 8) /4;
			 tank1X2 = obj.get(0).getX() - (TankGame.Width - 8) /4;
		}
		if (obj.get(0).getY() < TankGame.Height/2) {
			 tank1Y2 = 0;
			 tank1Y1 = TankGame.Height;
		} else if(obj.get(0).getY() > TankGame.GameHeight - TankGame.Height/2 ) {
			 tank1Y1 = TankGame.GameHeight;
			 tank1Y2 = TankGame.GameHeight - TankGame.Height;
		} else {
			 tank1Y1 = obj.get(0).getY() + TankGame.Height/2;
			 tank1Y2 = obj.get(0).getY() - TankGame.Height/2;
		}
		
		
		if (obj.get(1).getX() < (TankGame.Width - 8) /4) {
			 tank2X2 = 0;
			 tank2X1 = (TankGame.Width - 8)/2;
		} else if(obj.get(1).getX() > (TankGame.GameWidth - (TankGame.Width -8) /4) ) {
			 tank2X1 = TankGame.GameWidth;
			 tank2X2 = TankGame.GameWidth - (TankGame.Width - 8)/2;
		} else {
			 tank2X1 = obj.get(1).getX() + (TankGame.Width - 8) /4;
			 tank2X2 = obj.get(1).getX() - (TankGame.Width - 8)/4;
		}
		if (obj.get(1).getY() < TankGame.Height/2) {
			 tank2Y2 = 0;
			 tank2Y1 = TankGame.Height;
		} else if(obj.get(1).getY() > TankGame.GameHeight - TankGame.Height/2 ) {
			 tank2Y1 = TankGame.GameHeight;
			 tank2Y2 = TankGame.GameHeight - TankGame.Height;
		} else {
			 tank2Y1 = obj.get(1).getY() + TankGame.Height/2;
			 tank2Y2 = obj.get(1).getY() - TankGame.Height/2;
		}
		
	
		
			for(int i=0 ; i < obj.size(); i++) {				
				GameObject object = obj.get(i);				 
				if(tank1X2 <= object.getX() && object.getX() + object.getImageWidth() <= tank1X1 && tank1Y2 <= object.getY() && object.getY() <= tank1Y1) {								
					obj.get(i).render(g,tank1X2,tank1Y2);						
				}				
							
				if(tank2X2 <= object.getX() && object.getX() < tank2X1 && tank2Y2 <= object.getY() && object.getY() <= tank2Y1) {					
					obj.get(i).render(g,tank2X2-TankGame.Width/2 + 4,tank2Y2);				
				}
				
				if(object instanceof Tank) {
					((Tank) object).missileRender(g, tank1X1, tank1X2, tank1Y1, tank1Y2, tank2X1, tank2X2, tank2Y1, tank2Y2);
				}				
			}
	}		


	public void addObject(GameObject object) {
		obj.add(object);
	}

	public void removeObject(GameObject object) {
		obj.remove(object); 	
	}



}
