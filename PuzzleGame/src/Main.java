/*Libraries used
 * *java.awt.event.* : Event and Event listener 
 * java.util.Random : Generator of random number
 * javax.swing.* : Respond to an event generate by user 
 * 
 * Main as public class.
 * Main class consist of JFrame which is extend of swing.*.
 * 
 * 
 * */


import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class Main {
	
	//JFrame is used to control the created frame
	static JFrame frame;
	//Puzzle class is used to define a puzzle characteristic and behavior
	static Puzzle puzzle;
	
	//function call falls here
	public static void main(String [] args) {
		
		//a new JFrame have created with JFrame characteristic
		frame = new JFrame();
		
		//the frame size has been fixed
		frame.setSize(980,800);
		
		//Purpose of the random number is allow the system to randomly generate the image puzzle
		Random rand = new Random();
		
		//the 'n' is based on how many images available and choose the image within the numbers
		int  n = rand.nextInt(3) + 1;
		
		/*puzzle variable function as storing the image 
		 * ImageIcon is to create an un-initialize image 
		 * */
		puzzle = new Puzzle(new ImageIcon(Main.class.getResource("images"+n+".jpg")).getImage());
		
		//purpose of the add method is to show the item in the frame later on 
		frame.add(puzzle);
		
		//setLocationRelativeTo method is null to allocate the frame on the center of the system
		frame.setLocationRelativeTo(null);
		
		//This is to allow the user to Resize the frame 
		frame.setResizable(true);
		
		//setVisible is to allow the frame to be view by public
		frame.setVisible(true);
		
		/*
		 * addMouseListener is to enable the functionalities once it is clicked
		 * if addMouseListener is null, no action will be take 
		 * if the puzzle have not start it will be switch to start
		 * else if the puzzle mixing is true then set to false
		 * else will call the puzzle.onClick
		 * */
		frame.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (!puzzle.started){
					puzzle.start();
				}
				else if (puzzle.mixing){
					puzzle.mixing = false;
				}
				else puzzle.onClick(e);
			}
		});
	}

}
