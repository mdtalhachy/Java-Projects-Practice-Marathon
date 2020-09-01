package rocks.talha.bricksmash;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

//KeyListener for detecting the arrow keys when playing
//ActionListener for moving the ball
public class GamePlay extends JPanel implements KeyListener, ActionListener{
	
	//setting game properties
	private boolean play = false;
	private int score = 0;
	
	private int totalBricks = 21;
	
	private Timer timer;
	private int delay = 8;
	
	//starting postion of our slider
	private int playerX = 310;
	
	//starting postion of the ball
	private int ballPosX = 120;
	private int ballPosY = 350;
	
	//direction of the ball
	private int ballXDir = -1;
	private int ballYDir = -2;
	
	//creating variable to store object of MapGenerator
	private MapGenerator map;
	
	//constructor for the class
	public GamePlay() {
		map = new MapGenerator(3, 7);
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	//with the help of g, gonna draw different shapes like slider, ball, shapes 
	public void paint(Graphics g) {
		//background painting
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		//drawing map
		map.draw((Graphics2D) g); 
		
		//borders on 3 side
		//no border below because game ends if ball moves down the below border
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
		//scores display
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString("" + score, 590, 30);		
		
		//the paddle 
		g.setColor(Color.green);
		g.fillRect(playerX, 550, 100, 8);
		
		//the ball
		g.setColor(Color.yellow);
		g.fillOval(ballPosX, ballPosY, 20, 20);
		
		//game end function
		if(totalBricks <= 0) {
			play = false;
			ballXDir = 0;
			ballYDir = 0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("You Won!", 260, 300);
			
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to Restart", 230, 350);
		}
		
		//game over function
		if(ballPosY > 570) {
			play = false;
			ballXDir = 0;
			ballYDir = 0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("GAME OVER, SCORE: ", 190, 300);
			
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to Restart", 230, 350);
		}
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		
		if(play) {
			
			if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
				ballYDir = -ballYDir;
			}
			
			//detecting bricks
			A: for(int i=0; i < map.map.length; i++) {
				for(int j=0; j < map.map[0].length; j++) {
					if(map.map[i][j] > 0) {
						int brickX = j * map.brickWidth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 20, 20);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect)) {
							map.setBrickValue(0, i, j);
							totalBricks--;
							score += 5;
							
							if(ballPosX + 19 <= brickRect.x || ballPosX + 1 >= brickRect.x + brickRect.width) {
								ballXDir = -ballXDir;
							}else {
								ballYDir = -ballYDir;
							}
							
							break A;
						}
					}
				}
			}
			
			ballPosX += ballXDir;
			ballPosY += ballYDir;
			
			if(ballPosX < 0) {
				ballXDir =  -ballXDir;
			}
			if(ballPosY < 0) {
				ballYDir =  -ballYDir;
			}
			if(ballPosX > 670) {
				ballXDir =  -ballXDir;
			}
		}
		
		repaint();
	}

	//don't need these two methods
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		
		//if right arrow is pressed
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			
			//checking if the bar goes out of border
			if(playerX >= 600) {
				//keep it in the border
				playerX = 600;
			}else {
				moveRight();
			}
		}
		
		//if left arrow is pressed
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			
			//checking if the bar goes out of border
			if(playerX < 10) {
				//keep it in the border
				playerX = 10;
			}else {
				moveLeft();
			}
		}
		
		//if pressed enter
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!play) {
				
				play = true;
				ballPosX = 120;
				ballPosY = 350;
				ballXDir = -1;
				ballYDir = -2;
				playerX = 310;
				score = 0;
				totalBricks = 21;
				map = new MapGenerator(3, 7);
				
				repaint();
			}
		}
		
	}
	
	public void moveRight() {
		play = true;
		playerX += 20;
	}
	
	public void moveLeft() {
		play = true;
		playerX -= 20;
	}

	

}
