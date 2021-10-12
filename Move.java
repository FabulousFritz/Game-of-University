package application;

import java.util.Random;

/**
 * Determines how far the players move
 * @author Mallory
 *
 */
public class Move {

	// the value of what was spun on the wheel
	private int wheelSpin;
	private int min = 1;
	private int max = 10;
	
	/**
	 * Chooses a random number from 1-10
	 * @return wheelSpin
	 * 			A random number from 1-10 to represent the spin amount
	 */
	public int spin() {
		Random r = new Random();
		wheelSpin = r.nextInt((max - min) + 1) + min;
		return wheelSpin;
	}	
	
	/**
	 * Returns the spin amount
	 * @return wheelSpin
	 * 			The spin amount
	 */
	public int getWheelSpin() {
		return wheelSpin;
	}
	
	/**
	 * Translates a rotation amount into a move amount
	 * @param rotation
	 * 			A rotation amount
	 */
	public void setWheelSpin(int rotation) {
    	if ( rotation < 1098 ) wheelSpin = 3;
		else if ( rotation < 1134 ) wheelSpin = 2;
		else if ( rotation < 1170 ) wheelSpin = 1;
		else if ( rotation < 1206 ) wheelSpin = 10;
		else if ( rotation < 1242 ) wheelSpin = 9;
		else if ( rotation < 1278 ) wheelSpin = 8;
		else if ( rotation < 1314 ) wheelSpin = 7;
		else if ( rotation < 1350 ) wheelSpin = 6;
		else if ( rotation < 1386 ) wheelSpin = 5;
		else if ( rotation < 1422 ) wheelSpin = 4;
		else wheelSpin = 3;
    }

	/**
	 * Updates the player's location based on the move amount
	 * @param currentLocation
	 * 			An integer representing the current location of the player
	 * @return updated location
	 * 			An updated location
	 */
	public int updateLocation(int currentLocation) {
		return currentLocation + wheelSpin;
		}
		
}
