package com.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import java.awt.image.BufferStrategy;
import java.util.Random;


public class Game extends Canvas implements Runnable {

	private boolean  running;
	public static final int    WIDTH  = 640;
	public static final int    HEIGHT = WIDTH / 4 * 3;
	private Handler handler;
	private Random rand;
	private Health health;
	private Score score;


	public Game() {
		//all methods below should be called before window is created
		handler = new Handler();	
		this.addKeyListener(new KeyControl(handler));
		
		initWindow(this);
		
		health = new Health();
		score = new Score(handler, health);
		
		rand = new Random();
		handler.addObject(new Player(50,50,ID.Player,handler));
		handler.addObject(new Player(100,100,ID.Player2, handler));
		handler.addObject(new Enemy(rand.nextInt(WIDTH), rand.nextInt(HEIGHT),ID.Enemy));
	}
	
	
	public void initWindow(Game game) {	
		JFrame frame = new JFrame("Tank Game");
		frame.add(game);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
	
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.err.println("Exiting Game");
				game.stop();
			}
		});
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true);
		//frame.requestFocus();
		frame.setVisible(true);		
		game.start();		
	}
	
	

	private synchronized void start() {
		if (running)
			return;
		else
			running = true;
		new Thread(this, "TankGameMainThread").start();
		System.out.println("hi");
	}


	private synchronized void stop() {
		if (!running)
			return;
		else
			running = false;
		System.exit(0);
	}


	private void tick() {
		handler.tick();
		health.tick();
	}


	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		
		handler.render(g);
		health.render(g);

		g.dispose();
		bs.show();
	}


	public static int restrict(int value, int min, int max) {
		if(value>= max)
			return value = max;
		else if(value <= min)
			return value = min;
		else 
			return value;	
	} 
	
	
	

	@Override
	public void run() {
		double target = 60.0;
		double nsPerTick = 1000000000.0 / target;
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double unprocessed = 0.0;
		int fps = 0;
		int tps = 0;
		boolean canRender = false;

		while (running) {
			long now = System.nanoTime();
			unprocessed += (now - lastTime) / nsPerTick;
			lastTime = now;

			if (unprocessed >= 1.0) {
				tick();
				unprocessed--;
				tps++;
				canRender = true;
			} else canRender = false;

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (canRender) {
				render();
				fps++;
			}

			if (System.currentTimeMillis() - 1000 > timer) {
				timer += 1000;
				//System.out.printf("FPS: %d | TPS: %d\n", fps, tps);
				fps = 0;
				tps = 0;
			}

		}

		System.exit(0);
	}


	public static void main(String[] args) {
		
		 new Game();
		 
		//Game game = new Game();

//		JFrame frame = new JFrame("Tank Game");
//		frame.add(game);
//		frame.setSize(WIDTH, HEIGHT);
//		frame.setResizable(false);
//		frame.setFocusable(true);
//
//		frame.addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowClosing(WindowEvent e) {
//				System.err.println("Exiting Game");
//				game.stop();
//			}
//		});
//		frame.setLocationRelativeTo(null);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
//		frame.requestFocus();
//		game.start();
	}


}
