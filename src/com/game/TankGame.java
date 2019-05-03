package com.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class TankGame extends Canvas {

	private static final long serialVersionUID = 1L;
	public static final int Width = 1440;
	public static final int Height = 1080;
	public static final int GameWidth = 1920 ;
	public static final int GameHeight = 1330;
	private final String gameName = "Tank Game"; 
	private boolean running = false;
	private boolean startFlag = false;
	private GameController controller;
	private PlayerTank player_1 ;
	private PlayerTank player_2 ;
	private PowerUpGenerator generator;
	private Music bgMusic;
	private BufferedImage background = LoadImage.load("/Background.bmp"); 
	private Image TitleImage = LoadImage.load("/Title.bmp");
	private Image GameOverImage = LoadImage.load("/gameover.jpg");
	private Map map;
	private JFrame frame;
	private static boolean gameOver = false;	
	private String[] wallLayout = { 
			"UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU",
			"UNNNNNNNNNNNNNNBUNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNU",
			"UNNNNNNNNNNNNNNBUNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNU",
			"UNNNNNNNNNNNNNNBUNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNU",
			"UNNNNNNNNNNNNNNBUNNNNNNNNNNNNNNNNNNNNNNNBUBNNNNNNNNNNNNNNNNU",
			"UNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNBUBNNNNNNNNNNNNNNNNU",
			"UNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNBUBNNNNNNNNNNNNNNNNU",
			"UNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNBBBNNNNNNNNNNNNNNNNU",
			"UUUUUUUNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNBBBNNNNNNNNNNNNNNNNU",
			"UNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNUBNNNNBNNNNNNNNNNNNNNNNNU",
			"UNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNUBNNNBBBNNNNNNNNNNNNNNNNU",
			"UNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNUBNNNNNNNNNNNNNNNNNNNNNNU",
			"UNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNBBNNNNNNNNNNNNNNNNNNNNNNU",
			"UNNNNNNNNNNNNNNNBBBBBBBBBBBNNNNNNNNBBNNNBBUUUBBNNNNNNNNUUUUU",
			"UNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNBBNNNNNNNNNNNNNNNNNNUUUUU",
			"UNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNBBNNNNNNNNNNNNNNNNNNNNNNU",
			"UNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNBBNNNNNNNNNNNNNNNNNNNNNNU",
			"UNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNBBNNNNNNNNNNNNNNNNNNNNNNU",
			"UNNNNNNNNNNNNNNNNNNNNNNNNUNNNNNNNNNBBNNNNNNNNNNNNNNNNNNNNNNU",
			"UNNNNNNNNNNBBBBBBNNNNNNNNUNNNNNNNNNUBNNNNNNNNNNNNNNNNNNNNNNU",
			"UNNNNNNNNNNNNNUNNNNNNNNNNUNNNNNNNNNUBNNNNNNNNNNNNNNNNNNNNNNU",
			"UNNNNNNNNNNNNNUNNNNNNNUUUUNNNNNNNNNUBNNNNNNNNNNNNNNUNNNNNNNU",
			"UNNNNNNNNNNNNNBNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNUNNNNNNNU",
			"UNNNNNNNNNNNNNBNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNUNNNNNNNU",
			"UNNNNNNNNNNNNNBNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNUNNNNNNNU",
			"UNNNNNNNNNNNNNBNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNBBBBBBBBU",
			"UNNNNNNNNNNNNBUNNNNNNNNNNNNNNNNUNNNNNNNNNNNNNNNNNNNNNNNNNNNU",
			"UNNNNNNNNNNBBBBBBBBNNNNNNNNNNNNUNNNNNNNNNNNNNNNNNNNNNNNNNNNU",
			"UNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNUNNNNNNNNNNNNNBNNNNNNNNNNNNNU",
			"UNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNUNNNNNNNNNNNNNBNNNNNNNNNNNNNU",
			"UNNNNNNNNNNNNNNNNNNNNNNNNNNNBBBBBBBBBBNNNNNNNBNNNNNNNNNNNNNU",
			"UNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNBNNNNNNNNNNNNNU",
			"UNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNBNNNNNNNNNNNNNU",
			"UNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNBNNNNNNNNNNNNNU",
			"UNNNNNNNNNNNNUNNNNNNNNNNNNNNNNNNNNNNNBBBBBBBBBUUUUUNNNNNNNNU",
			"UNNNNNNNNNNUUUUUUUUNNNNNNNNNNNNNNNNNNNNNNNNNNUNNNNNNNNNNNNNU",
			"UNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNUNNNNNNNNNNNNNU",
			"UNNNNNNNNNNNNNBNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNU",
			"UNNNNNNNNNNNNNBNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNU",
	        "UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU"};



	public TankGame() {
		controller = new GameController();

		player_1 = new PlayerTank(200, 100, "Player_1");
		player_2 = new PlayerTank(1800, 500, "Player_2");
		controller.addObject(player_1);
		controller.addObject(player_2);
		bgMusic = new Music();
		bgMusic.playMusic();

		this.addKeyListener(new KeyControl(player_1, 38, 40, 37, 39, 10));  
		this.addKeyListener(new KeyControl(player_2, 87, 83, 65, 68, 32));
		

		for(int i=0; i < wallLayout.length; i++ ) {
			String temp = wallLayout[i];
			for(int j = 0; j < temp.length(); j++ ) {
				if(temp.charAt(j) == 'U') {
					controller.addObject(new UnbreakableWall(j*32,i*32));
				}
				else if(temp.charAt(j) == 'B') {
					controller.addObject(new Wall(j*32,i*32));
				}
				else if(temp.charAt(j) == 'N') {
				}
			}
		}
		map = new Map(controller);		
		this.initWindow(this);	
	}


	public void initWindow(TankGame game) {	
		frame = new JFrame(game.gameName);		
		frame.setSize(Width, Height);
		frame.setResizable(false);			
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true);		
		JButton button = new JButton();
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(TitleImage, 0, 0,Width, Height, null);
			}        
		};
		panel.setBackground(Color.BLACK);
		panel.setLayout(null);
		panel.add(button);
		button.setText("START");
		button.setSize(500, 100);
		button.setLocation(470, 850);
		button.setFont(new Font("Sans serif" , 0, 25));		
		button.setBackground(Color.darkGray);
		button.setForeground(Color.green);
		button.setVisible(true);
		button.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				startFlag = true;
			}			
		});
		panel.setLocation(0,0);
		panel.setSize(Width, Height);			
		panel.setVisible(true);		
		frame.add(panel);
		frame.setVisible(true);	
		requestFocus();
		while(true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}			
			if(startFlag) {
				frame.remove(panel);
				frame.add(map);
				frame.add(game);
				frame.pack();		
				map.setLocation(420,620);
				requestFocus();	
				frame.setSize(Width, Height);
				game.start();
				startFlag = false;
			}
		}		
	}


	private void update() {
		controller.update();	
	}

	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();	
		g.setColor(Color.BLACK);		
		g.fillRect(0, 0, Width, Height);

		if(gameOver) {
			String player ;
			if(player_1.getScore() > player_2.getScore()) {
				player = "Player One";
			}
			else {
				player = "Player Two";
			}
			frame.remove(map);

			g.drawImage(GameOverImage, 0, 0, Width, Height, null);
			g.setColor(Color.WHITE);

			g.setFont(new Font("arial Bold" , 0, 60));
			g.drawString(player + " wins!", 450, 750);

		}
		else {
			g.drawImage(background, 0, 0,(Width-8)/2, Height, null);
			g.drawImage(background, (Width/2)+4, 0,(Width-8)/2, Height, null);
			map.repaint();
			controller.render(g);			
		}
		g.dispose();
		bs.show();			
	}

	public void start() {
		final int ticksPerSecond = 60;
		final int timePerTick = 1000/ticksPerSecond;
		final int maxSkip = 5;
		long nextTick = System.currentTimeMillis();
		int loops ;
		running = true;	

		generator = new PowerUpGenerator();
		new Thread(generator).start();		
		while(running) {			
			loops = 0;
			while(System.currentTimeMillis() > nextTick  && loops < maxSkip) {
				this.removeObjects();
				this.addPowerups();				
				update();
				nextTick += timePerTick;
				loops++;				
			}				
			render();				
		}		
	}	


	public static void main(String[] args) {		
		new TankGame();
	}

	public void removeObjects() {		
		for(int i=0 ; i < controller.getObj().size(); i++) {
			GameObject object = controller.getObj().get(i);
			if(object.getRemoval()) {				
				controller.removeObject(object);
			}			
		}
	}

	public void addPowerups() {
		Random randX = new Random();
		Random randY = new Random();
		Rectangle rWall = new Rectangle();
		int tempX = randX.nextInt(Width);
		int tempY = randY.nextInt(Height);
		boolean intersect = true;

		while(intersect){
			Rectangle r = new Rectangle(tempX, tempY,64,64);
			for(int i = 0; i < controller.getObj().size();i++) {				
				GameObject object = controller.getObj().get(i);
				if( object instanceof Wall ||object instanceof UnbreakableWall) {
					rWall = object.getRectangle();					
				} 				
				if(r.intersects(rWall)) {
					intersect = true;
					tempX += 10;
					tempY += 10;
					break;
				} else intersect = false;
			}	
		}

		if(generator.getHealthPower()) {			
			controller.addObject(new Power(tempX , tempY, PowerType.PowerHealth));
			generator.setHealthPower(false);
		} else if (generator.getRocketPower()) {
			controller.addObject(new Power(tempX, tempY,PowerType.PowerRocket ));
			generator.setRocketPower(false);			
		} else if(generator.getShieldPower()) {
			controller.addObject(new Power(tempX,tempY, PowerType.PowerShield));
			generator.setShieldPower(false);
		}			
	}

	public static void setGameOver(boolean gameOver) {
		TankGame.gameOver = gameOver;	
	}


}


