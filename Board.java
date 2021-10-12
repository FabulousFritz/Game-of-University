package application;

import java.util.ArrayList;

/**
 * Controls the board settings, and collects the list of spaces.
 * @author Celia
 */
public class Board{
	//Create a list of all spaces
	private ArrayList<Space> spaceList;
	private int length = 48;
	
	/**
	 * Constructor
	 * Creates the list of spaces needed for the board.
	 */
	public Board(){
		spaceList = new ArrayList<Space>();
		for (int indexOfSpace = 0; indexOfSpace < length; indexOfSpace++) {
			spaceList.add(new Space(indexOfSpace)) ;
		}
	}	
	
	/**
	 * Returns the value of money the player will gain or lose when they 
	 * land on the space. 
	 * @param index			the index of the space the player landed on, from the spaceList.
	 * @return spaceVal		the value of money the player will gain or lose.
	 */
	public int getSpaceAt(int index) {
		Space currentSpace = spaceList.get(index);
		return currentSpace.getSpaceVal();
	}
	
	/** 
	 * Gets the length of the board. 
	 * @return length-1		The last index in the spaceList.
	 */
	public int getLength(){
		return length-1;
	}
	
	/** 
	 * Sets the spaceList.
	 * @param spaceList		The intended spaceList.
	 */	
	public ArrayList<Space> getSpaceList() {
		return spaceList;
	}
	
}