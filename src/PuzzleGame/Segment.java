package PuzzleGame;
/*Library used
 * java.awt.* : consist of components for graphic, images and also interface
 * A class named Segment will have all the characteristic of the Segment which will be called in Puzzle class
 * */

import java.awt.*;

public class Segment {
	//gameInstance ,ID , segmentSize and pos has been initialize
	private Puzzle gameInstance;
	private int ID;
	private int segmentSize;
	private Point pos;
	
	//isEmpty has been initialize to false
	public boolean isEmpty = false;

	/*
	 * Parameterize methods
	 * instance, ID and segmentSize has been passed into the method to set their default value
	 * pos is set with a new location x,y 
	 * (ID <= 2)? ID:(ID <= 5)? (ID-3):(ID-6)) this is conditional operation
	 * if the condition is (ID <= 2) determine whether the result is ID( true) or (ID <= 5) (false)
	 * the ceil will compare the value in it and return the smallest floating point value which is small or equal to the value in it
	 * 
	 * */
	public Segment(Puzzle instance, int ID, int segmentSize) {
		this.gameInstance = instance;
		this.ID = ID;
		this.segmentSize = segmentSize;
		pos = new Point(((ID <= 2)? ID:(ID <= 5)? (ID-3):(ID-6)), (int) Math.ceil((ID/3)));
	}
	
	/*If p lies within the segment (if the segment got clicked) returns true
	 *return x,y,height, width to the Rectangle
	 *contains(p) is to determine whether the Rectangle consist of specific point
	 *if there is point return 
	 *the boolean will return true
	 * 
	 */
	public boolean hitten(Point p) {
		return new Rectangle(pos.x*segmentSize, pos.y*segmentSize, segmentSize, segmentSize).contains(p);
	}
	
	
	//Move the Segment to a new position if possible
	public boolean move(Point p) {
		boolean isPossible = false;
		if (p.x == pos.x+1 && p.y == pos.y) isPossible = true;
		if (p.x == pos.x-1 && p.y == pos.y) isPossible = true;
		if (p.x == pos.x && p.y == pos.y+1) isPossible = true;
		if (p.x == pos.x && p.y == pos.y-1) isPossible = true;
		
		if (isPossible) {
			pos = p;
		}
		
		return isPossible;
	}

	/*
	 * Graphic g is to store the location or coordinates
	 * 
	 * if isEmpty is false 
	 * it will carry out the statements in the condition 
	 * 
	 * 
	 * */
	public void paint(Graphics g) {
		if (!isEmpty) {
			int x = ((ID <= 2)? ID:
				(ID <= 5)? (ID-3):(ID-6));
			int y = (int) Math.ceil((ID/3));
			g.drawImage(gameInstance.img, pos.x*segmentSize, pos.y*segmentSize, (pos.x+1)*segmentSize, (pos.y+1)*segmentSize, x*segmentSize, y*segmentSize, (x+1)*segmentSize, (y+1)*segmentSize, null);
			g.setColor(new Color(0,0,0));
			g.drawLine(pos.x*segmentSize+(segmentSize-1), pos.y*segmentSize, pos.x*segmentSize+(segmentSize-1), (pos.y+1)*segmentSize);
			g.drawLine(pos.x*segmentSize, pos.y*segmentSize+(segmentSize-1), (pos.x+1)*segmentSize, pos.y*segmentSize+(segmentSize-1));
			g.setColor(new Color(150,150,150, 100));
			g.drawLine(pos.x*segmentSize, pos.y*segmentSize, pos.x*segmentSize, (pos.y+1)*segmentSize);
			g.drawLine(pos.x*segmentSize, pos.y*segmentSize, (pos.x+1)*segmentSize, pos.y*segmentSize);
		}
	}

	/*
	 * getPosition 
	 * this is to get the pos (x,y) location
	 * */
	public Point getPosition() {
		return this.pos;
	}
	
	/*
	 * the value passed into the setPosition method is stored in the this.pos variable
	 * */
	public void setPosition(Point p) {
		this.pos = p;
	}

	/*
	 * getID method is to return the ID
	 * */
	public int getID() {
		return ID;
	}
}
