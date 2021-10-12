package application;

import javafx.scene.shape.Circle;

/**
 * Used to store and update information about the player
 * @author Jasmine
 *
 */
public abstract class Player{
	private String name;
	private String color;
	private int bankAccount;
	private int currentLocation = 0;
	private int offSetX;
	private int offSetY;
	private int gridX;
	private int gridY;
	private int[][] locationList;
	private Circle icon;
	private String type;

	
	/**
	 * Return the player's name
	 * @return
	 * 			Name of the player
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the player's name
	 * @param name
	 * 			Name of the player
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the color of the player icon
	 * @return color
	 * 			The color of the player icon
	 */
	public String getColor() {
		return color;
	}
	
	/**
	 * Sets the color of the player icon
	 * @param color
	 * 			The color you want to set he player icon too
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	/**
	 * Returns the bank account balance of the player
	 * @return bankAccount
	 * 			The balance of the player's bank account
	 */
	public int getBankAccount() {
		return bankAccount;
	}
	
	/**
	 * Sets the bank account balance of the player
	 * @param bankAccount
	 * 			The new bank account balance
	 */
	public void setBankAccount(int bankAccount) {
		this.bankAccount = bankAccount;
	}
	
	/**
	 * Returns the current location of the player on the board
	 * @return currentLocation
	 * 			The current location of the player on the board
	 */
	public int getCurrentLocation() {
		return currentLocation;
	}
	
	/**
	 * Sets the current location of the player on the board
	 * @param currentLocation
	 * 			The new location of the player on the board
	 */
	public void setCurrentLocation(int newLocation) {
		currentLocation = newLocation;
	}	
	
	/**
	 * Returns the off set amount for the X coordinate
	 * @return offSetX
	 * 			The off set amount for the X coordinate
	 */
	public int getOffSetX() {
		return offSetX;
	}
	
	/**
	 * Sets the off set amount for the X coordinate
	 * @param offSetX
	 * 			The off set amount for the X coordinate
	 */
	public void setOffSetX(int offSetX) {
		this.offSetX = offSetX;
	}
	
	/**
	 * Returns the off set amount for the Y coordinate
	 * @return offSetY
	 * 			The off set amount for the Y coordinate
	 */
	public int getOffSetY() {
		return offSetY;
	}
	
	/**
	 * Sets the off set amount for the Y coordinate
	 * @param offSetY
	 * 			The off set amount for the Y coordinate
	 */
	public void setOffSetY(int offSetY) {
		this.offSetY = offSetY;
	}
	
	/**
	 * Returns the current x coordinate of the player on the grid
	 * @return
	 * 			The current x coordinate of the player on the board
	 */
	public int getGridX() {
		return gridX;
	}
	
	/**
	 * Sets a new x coordinate as the current x coordinate of the player on the grid
	 * @param gridX
	 * 			The new x coordinate on the grid
	 */
	public void setGridX(int gridX) {
		this.gridX = gridX;
	}
	
	/** 
	 *Returns the current y coordinate of the player on the grid 
	 * @return
	 * 			The current y coordinate of the player on the board
	 */
	public int getGridY() {
		return gridY;
	}
	
	/**
	 * Sets a new y coordinate as the current x coordinate of the player on the grid
	 * @param gridY
	 * 			The new x coordinate on the grid
	 */
	public void setGridY(int gridY) {
		this.gridY = gridY;
	}
	
	/**
	 * Sets the locationList
	 * @param locationList
	 * 			2D array that contains the location of all the game squares
	 */
	public void setLocationList(int[][] locationList) {
		this.locationList = locationList;
	}
	
	/**
	 * Sets the player's icon
	 * @param icon
	 * 			The circle representing the player's icon
	 */
	public void setIcon(Circle icon) {
		this.icon = icon;
	}
	
	/**
	 * Return the player's name
	 * @return
	 * 			Name of the player
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Sets the player's name
	 * @param name
	 * 			Name of the player
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Initializes a Player with a specified name
	 * @param name
	 * 			Name of the player
	 */
	public Player(String name) {
		setName(name);
		setBankAccount(100);
		setCurrentLocation(0);
	}
	
	/**
	 * Initializes a Player with a specified name and color
	 * @param name
	 * 			Name of the player
	 * @param color
	 * 			Color of the player icon
	 */
	public Player(String name, String color) {
		setName(name);
		setBankAccount(100);
		setCurrentLocation(0);
		setColor(color);
	}
	
	/**
	 * Creates a deep copy of a player
	 * @param toCopy
	 * 			The player you want to make a deep copy of
	 */
	public Player(Player toCopy) {
		setName(toCopy.getName());
		setBankAccount(toCopy.getBankAccount());
		setCurrentLocation(toCopy.getCurrentLocation());
	}
	
	/**
	 * Moves the player's icon based on a given amount
	 * @param i
	 * 		How much to move the player by
	 */
	public void move(int i) {
		if((currentLocation + i) < locationList.length) {
			setCurrentLocation(currentLocation + i);
		}else {
			currentLocation = GameScreen.lastGameSpace;
		}
		gridX = locationList[currentLocation][0];
		gridY = locationList[currentLocation][1];
		icon.setLayoutX((gridX*GameScreen.recSize)+offSetX);
		icon.setLayoutY((gridY*GameScreen.recSize)+offSetY);
	}
	
	/**
	 * Updates player's location and bankAccount
	 * @param location
	 * 			The new location of the player on the game board
	 * @param val
	 * 			The monetary value of the new location
	 */
	public void updateLocationAndVal(int location, int val) {
		setCurrentLocation(location);
		bankAccount += val;
	}
	
	/**
	 * Adds an additional amount to the player's bankAccount
	 * @param val
	 * 			The amount to add to the bankAccount
	 */
	public void updateVal(int val) {
		bankAccount += val;
	}
	
	/**
	 * Updates the player information and board state
	 * @param board
	 * 			The board the player interacts with during their turn
	 */
	public void executeTurn(Board board) {
		Move move = new Move();
		int moveAmount = move.spin();
		System.out.println("You have spun a " + moveAmount + "!");
		System.out.println("");
		int newLocation = move.updateLocation(currentLocation);
		//makes sure the player doesn't go past the length of the board
		if(newLocation >= board.getLength()) {
			newLocation = board.getLength();
			System.out.println("You have reached the end of the board!") ;
		}
		int val = board.getSpaceAt(newLocation);
		//prints appropriate statements based on the value of the space
		if(val > 0) {
			System.out.println("You gained $" + val +"!") ;
		} else if (val < 0) {
			System.out.println("You lost $" + -1 * val + "!") ;
		} else {
			System.out.println("You're safe on this space!") ;
		}
		updateLocationAndVal(newLocation , val) ;
		System.out.println("Your bank account is now $" + this.getBankAccount() + "!") ;
	}
	
	
	/**
	 * Plays the player's turn
	 * @param board
	 * 			The board the player interacts with during their turn
	 */
	public abstract void playTurn(Board board);




}
