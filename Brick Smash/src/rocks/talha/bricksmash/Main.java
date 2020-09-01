package rocks.talha.bricksmash;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame obj = new JFrame();
		
		//setting properties to JFrame window aka game console 
		obj.setBounds(10, 10, 700, 600);
		obj.setTitle("Breakout Ball");
		obj.setResizable(false);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//creating object of GamePlay and adding it as panel to the JFrame window
		GamePlay gamePlay = new GamePlay();
		obj.add(gamePlay);
		
		obj.setVisible(true);
	}

}
