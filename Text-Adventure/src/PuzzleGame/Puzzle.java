package PuzzleGame;
/*Libraries used
 * *java.awt.* : Event and Event listener 
 * java.awt.event.*: consist of framework, time , date and events
 * javax.swing.* : Respond to an event generate by user 
 * 
 * A class named Puzzle which extends JPanel
 * 
 * */


import java.awt.*;

import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class Puzzle extends JPanel {
	
	//segment variable which is an array
	//img variable which is an image 
	
	Segment[] segments;
	Image img;
	
	//started variable is to ensure whether the puzzle has started
	//mixing variable is to ensure whether the puzzle has mixed up
	public boolean started = false;
	public boolean mixing = false;
	
	/* state for checking whether timer is paused or not
	 * if puzzle paused, state = false,
	 * if puzzle not paused, state = true
	 * */
	public static boolean state = true;
	public static String userProgress = "Progress";
	
	//variable for checking whether puzzle is completed
	public static boolean complete = false;

	/*
	 * Parameterize methods
	 * Pass in variable is the img 
	 * initialize 9 segments
	 * getWidth method is to return the width of the image or null as default value
	 * segmentSize is the size of the small piece of the image 
	 * for loop is to pass in each small images into the segments array
	 * */
	public Puzzle(Image img) {
		this.img = img;
		
		segments = new Segment[9];
		int segmentSize = img.getWidth(null)/3;
		for (int i = 0; i != segments.length; i++) {
			//Pass the Segment the instance of the Game, its position (by the index) and its size
			segments[i] = new Segment(this, i, segmentSize);
		}
	}
	
	public void setState(boolean s){
		state = s;
	}
	
	//check if puzzle is completed.. not used at all
	public boolean isComplete(){
		return complete;
	}
	
	public void setUserProgress(String p){
		userProgress = p;
	}
	
	public String getUserProgress(){
		return userProgress;
	}
	
	/*
	 * start method 
	 * when the segment 9 on the bottom right is empty the mix will start 
	 * 
	 * */
	public void start() {
		started = true;
		//remove segment 9 (the one in the bottom right)
		segments[8].isEmpty = true;
		
		//if puzzle is already completed, dont allow to mix again
		if (complete == false)
			mix.start();
	}
	
	
	/*
	 * mix variable has been created 
	 * the variable consist of characteristic of the thread
	 * Thread is to switching one of the neighboring segments with the targeted segment
	 * 
	 * when the mixing is true 
	 * the segment swapping will start by using for loop 
	 * 
	 * the first try method is to get the random position of the segment 
	 * the random position of segment is stored in temp variable 
	 * the original random position of segment have the segment 8 value stored in it 
	 * While the segment 8 consist of the temp value
	 * */
	//This Thread shuffles the Segments by switching one of the neighboring segments of the empty one with the empty one
	Thread mix = new Thread(new Runnable() {
		public void run() {
			mixing = true;
			while (mixing == true) {
				ArrayList<Integer> possibleMovements = new ArrayList<Integer>();
				for (Segment s : segments) {
					if (s.getPosition().x == segments[8].getPosition().x+1 && s.getPosition().y == segments[8].getPosition().y) possibleMovements.add(s.getID());
					if (s.getPosition().x == segments[8].getPosition().x-1 && s.getPosition().y == segments[8].getPosition().y) possibleMovements.add(s.getID());
					if (s.getPosition().x == segments[8].getPosition().x && s.getPosition().y == segments[8].getPosition().y-1) possibleMovements.add(s.getID());
					if (s.getPosition().x == segments[8].getPosition().x && s.getPosition().y == segments[8].getPosition().y+1) possibleMovements.add(s.getID());
				}
				
				int ind = (int) ((Math.random()*possibleMovements.size()));
				try {
					Point tmp = segments[possibleMovements.get(ind)].getPosition();
					segments[possibleMovements.get(ind)].setPosition(segments[8].getPosition());
					segments[8].setPosition(tmp);
					
					//throw new Exception("Error occur");
				} 
				
				catch (Exception e) {
					//System.out.println(e.getMessage());
				}
				
				repaint();
				
				try {
					Thread.sleep(10);
					
					//throw new InterruptedException("Thread has been interrupted");
				} 
				
				catch (InterruptedException e) {
					
					//System.out.println(e.getMessage());
				}
			}
		}
	});
	
	/*
	 * for loop method is applied 
	 * when the Segment s is hit, the MouseEvent e will get the x,y position 
	 * tmp will get the x,y position of the Segment get hit
	 * if segments[8] , which is the Segment 9 gets move , the segments 8 will have position of the tmp
	 * 
	 * the boolean is to check if user is done by comparing it's position with the one it should have
	 * the for loop is to check whether the user move it correctly
	 * if move correctly there will be a smiley face
	 * else it will have a sad face 
	 * 
	 * if the puzzle is not solve the last bottom right segment will not appear
	 * */
	
	//Check if the user clicked onto a seg,emt amd of possible move it onto the empty one
	public void onClick(MouseEvent e) {
		//if puzzle is not paused and is not completed yet, allow onClick events
		if (state != false && complete == false){
			for (Segment s : segments) {
				if (s.hitten(e.getPoint())) {
					Point tmp = s.getPosition();
					if (s.move(segments[8].getPosition())) {
						segments[8].setPosition(tmp);
						
						boolean done = true;
						for (int i = 0; i != 9; i++) {
							if (segments[i].getPosition().x == ((i <= 2)? i:(i <= 5)? (i-3):(i-6)) && segments[i].getPosition().y == (int) Math.ceil((i/3))) {
								System.out.println(i+": :)");
							} else {
								System.out.println(i+": :(");
								done = false;
							}
						}
						
						//what happens when puzzle is completed
						if (done) {
							started = false;
							segments[8].isEmpty = false;
							complete = true;
						}
					}
				}
			}
			repaint(); 
			//schedule component for redrawing 
			//control the update()to paint() cycle
			//repaint can't be override
		}
	}
	
	/*
	 * 
	 * */
	public void paint(Graphics g) {
		super.paint(g);
		for (int i = 0; i != segments.length; i++) {
			//System.out.print(segments[i].getID()+((i == 2 || i == 5 || i == 8)? "\n-----\n":"|"));
			segments[i].paint(g);
		}
	}
	
}
