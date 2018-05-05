
import java.util.Scanner;
import java.util.Random;
public class GameDriver {
	
	/**
	 * Check if the user input is within the size of the battlefield
	 *
	 * @param input The user input captured from keyboard
	 * @param  gridSize The size of the battlefield to check against
	 * @return A boolean to tell if the input is valid
	 */
	public static boolean checkInput(String input, int gridSize){
		int row = getRow(input);
		int col = getCol(input);
		if ((row >= 0 && row < gridSize) && (col >= 0 && col < gridSize)){
			return true;
		}
		else {
			System.out.println("sorry, coordinates outside the grid. try again.");
			return false;
		}
	}
	/**
	 * To convert user input into row index of the 2 by 2 array of item object
	 *
	 * @param input The user input captured from keyboard
	 * @return An integer converted to the index number of row
	 */
	public static int getRow(String input){
		return ((int) input.charAt(1)) - 49; //use type cast to convert input string number to index location of the array
	}
	/**
	 * To convert user input into column index of the 2 by 2 array of item object
	 *
	 * @param input The user input captured from keyboard
	 * @return An integer converted to the index number of column
	 */
	public static int getCol(String input){
		return ((int) Character.toUpperCase(input.charAt(0))) - 65; //use type cast to convert input string letter to index location of the array
	}
	/**
	 * To tell if the game should end
	 *
	 * @param human The human gamer object
	 * @param computer The computer gamer object
	 * @param nbShip The total number of ships per party
	 * @return A boolean to tell if the game should finish
	 */
	public static boolean gameFinished (Gamer human, Gamer computer, int nbShip){
		return ( human.getSunkShip() >= nbShip || computer.getSunkShip() >= nbShip ); //the game ends if either player have more than the defined number of sunken ship
	}
	/**
	 * To display the unmasked battlefield maps
	 * and print out total missed turn stats for both party
	 *
	 * @param human The human gamer object
	 * @param computer The computer gamer object
	 * @param grid The gaming grid
	 */
	public static void gameEnd (Gamer human, Gamer computer, Item[][] grid){
		printCurrentGrid(grid,false);
		System.out.println("You missed "+human.getTotalMissedTurn() +" turns in total");
		System.out.println("The computer missed "+computer.getTotalMissedTurn() +" turns in total");
	}
	/**
	 * Check if either party have won the game
	 * and print end of game message
	 *
	 * @param human The human gamer object
	 * @param computer The computer gamer object
	 * @param nbShip The total number of ships per party
	 * @param grid The gaming grid
	 * 
	 */
	public static void winnerCheck (Gamer human, Gamer computer, int nbShip, Item[][] grid){
		if (computer.getSunkShip() >= nbShip){ // if computer have more than defined number of sunken ship
			System.out.print("  You Win !");
			gameEnd(human,computer,grid);
			
		}
		else if (human.getSunkShip() >= nbShip){ // if the player have more than defined number of sunken ship
			System.out.print("  Sorry the Computer Win.");
			gameEnd(human,computer,grid);
		}
		else{ // print out masked grid if neither party have won after this round of game
			printCurrentGrid(grid,true); 
		}
	}
	/**
	 * To print out battlefield maps
	 *
	 * @param grid The gaming grid
	 * @param hideUncalledPosition If the printed grid should mask uncalled position during game play
	 * it will print out unmasked grid if it is false
	 */
	public static void printCurrentGrid (Item[][] grid,boolean hideUncalledPosition){
		System.out.println("\n");
		for(int i=0; i<grid.length;i++){
			System.out.print("\t");
			for(int j=0;j<grid[i].length;j++){
				if (!grid[i][j].getCalled() && hideUncalledPosition){ // if the position hasn't been called before, print out masking its real identity
					System.out.print("_ ");
				}
				else{ // otherwise print out the true identity of the position
					System.out.print(grid[i][j]);
				}
			}
			System.out.print("\n");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		
		final int NUM_SHIP = 6; // number of ships per party
		final int NUM_GRENADE = 4; // number of grenades per party
		final int GRID_SIZE = 8; // the grid size of the gaming map
		
		Item[][] grid = new Item[GRID_SIZE][GRID_SIZE];
		
		for(int i=0; i<grid.length;i++){ //initialize the 2 by 2 array of gaming map
			for(int j=0;j<grid[i].length;j++){
				grid[i][j] = new Item();
			}
		}
		
		Gamer human = new Gamer ("human");
		Gamer computer = new Gamer ("computer");
		
		Scanner keyboard = new Scanner(System.in); //Setting up the keyboard input
		Random generator = new Random(); //Setting up the random number generator
		
		System.out.println("Hi, let's play Battleship!\n");
		
		
		//Game Initialization
		
		for (int i = 0; i < NUM_SHIP ;){ //User Ship Generation
			System.out.print("Enter the coordinates of your ship #" + (i+1)+": ");
			String input = keyboard.next();
			int row = getRow(input);
			int col = getCol(input);
			
			if (!checkInput(input, GRID_SIZE)){ //Check if input is within the grid
				continue;
			}
			else if (grid[row][col].getType() != Type.EMPTY){ //Check if the position had already been place with another ship or grenade
				System.out.println("sorry, coordinates already used. try again.");
			}
			else { //if its okay to place the item, put it there
				grid[row][col].setType(Type.SHIP);
				grid[row][col].setOwner(human);
				i++;
			}
		}
		System.out.println();
		
		for (int i = 0; i < NUM_GRENADE ;){ //User Grenade Generation
			System.out.print("Enter the coordinates of your grenade #" + (i+1)+": ");
			String input = keyboard.next();
			int row = getRow(input);
			int col = getCol(input);
			
			if (!checkInput(input, GRID_SIZE)){ //Check if input is within the grid
				continue;
			}
			else if (grid[row][col].getType() != Type.EMPTY){ //Check if the position had already been place with another ship or grenade
				System.out.println("sorry, coordinates already used. try again.");
			}
			else { //if its okay to place the item, put it there
				grid[row][col].setType(Type.GRENADE);
				grid[row][col].setOwner(human);
				i++;
			}
		}
		
		for (int i = 0; i < NUM_SHIP ;){ //Computer Ship Generation
			int row = generator.nextInt(8); //Generate random number within the index of the grid
			int col = generator.nextInt(8);
			
			if (grid[row][col].getType() == Type.EMPTY){ // if the position is free of grenades or ships, place the item there
				grid[row][col].setType(Type.SHIP);
				grid[row][col].setOwner(computer);
				i++;
			}
		}
		
		for (int i = 0; i < NUM_GRENADE ;){ //Computer Grenade Generation
			int row = generator.nextInt(8);
			int col = generator.nextInt(8);
			
			if (grid[row][col].getType() == Type.EMPTY){ // if the position is free of grenades or ships, place the item there
				grid[row][col].setType(Type.GRENADE);
				grid[row][col].setOwner(computer);
				i++;
			}
		}
		
		System.out.println("\nOK, the computer placed its ships and grenades at random. Let's play.");
		
		//Game Start
		
		boolean isHumanTurn = true; // human player takes the first turn
		
		while ( !gameFinished(human, computer, NUM_SHIP) ){ //the game ends if either player have more than the defined number of sunken ship
			while (isHumanTurn && (!gameFinished(human, computer, NUM_SHIP))){ // Loop until it's the computer's turn
				System.out.print("position of your rocket: ");
				String input = keyboard.next();
				if (checkInput(input, GRID_SIZE)){ //Check if user input is within grid
					int row = getRow(input);
					int col = getCol(input);
					
					if (grid[row][col].getCalled()){ //If the position has already been called
						System.out.print("position already called.");
					}
					
					else {
						if (grid[row][col].getType() == Type.SHIP){ //If the position is a ship
							if (grid[row][col].getOwner().getName().equals("human")){ //If the ship belongs to human player
								human.setSunkShip(human.getSunkShip() + 1); //Number of sunken ship of human player increase by 1
							}
							if (grid[row][col].getOwner().getName().equals("computer")){//If the ship belongs to computer
								computer.setSunkShip(computer.getSunkShip() + 1); //Number of sunken ship of computer increase by 1
							}
							System.out.print("ship hit.");
						}
						
						if (grid[row][col].getType() == Type.GRENADE){ //If the position is a grenade
							computer.setAdditionalTurnDue(computer.getAdditionalTurnDue() + 1); //Computer will play an extra turn
							human.setTotalMissedTurn(human.getTotalMissedTurn() + 1); //Human player's total missed turn will increase by 1
							System.out.print("boom! grenade.");
						}
						
						if (grid[row][col].getType() == Type.EMPTY){ //If the position is empty
							System.out.print("nothing.");
						}
						grid[row][col].hit(); //Mark the position already called
					}
					if (human.getAdditionalTurnDue() > 0){ //if human player have outstanding extra turn due
						human.setAdditionalTurnDue(human.getAdditionalTurnDue() - 1); //Play an extra turn
					}
					
					else { //Otherwise, it's the computer's turn
						isHumanTurn = false;
					}
					winnerCheck(human,computer,NUM_SHIP,grid); //Check if either party have won the game
				}
		}
			
		while (!isHumanTurn && (!gameFinished(human, computer, NUM_SHIP))){ //When it's computer's turn
			System.out.print("position of my rocket: ");
			int row = generator.nextInt(8); //Generate random index number within the grid
			int col = generator.nextInt(8);
			System.out.print((char)(col+65)); //Type cast it into grid number by game rules and print it out
			System.out.println((char)(row+49));
			
			if (grid[row][col].getCalled()){ //If the position has already been called
				System.out.print("position already called.");
			}
			
			else {
				if (grid[row][col].getType() == Type.SHIP){ //If the position is a ship
					if (grid[row][col].getOwner().getName().equals("human")){ //If the ship belongs to human player
						human.setSunkShip(human.getSunkShip() + 1); //Number of sunken ship of human player increase by 1
					}
					if (grid[row][col].getOwner().getName().equals("computer")){ //If the ship belongs to computer
						computer.setSunkShip(computer.getSunkShip() + 1); //Number of sunken ship of computer increase by 1
					}
					System.out.print("ship hit.");
				}
				
				if (grid[row][col].getType() == Type.GRENADE){ //If the position is a grenade
					human.setAdditionalTurnDue(human.getAdditionalTurnDue() + 1); //human player will play an extra turn
					computer.setTotalMissedTurn(computer.getTotalMissedTurn() + 1); //computer's total missed turn will increase by 1
					System.out.print("boom! grenade.");
				}
				
				if (grid[row][col].getType() == Type.EMPTY){ //If the position is empty
					System.out.print("nothing.");
				}
				grid[row][col].hit(); //Mark the position already called
			}
			if (computer.getAdditionalTurnDue() > 0){ //if computer have outstanding extra turn due
				computer.setAdditionalTurnDue(computer.getAdditionalTurnDue() - 1); //Play an extra turn
			}
			
			else {
				isHumanTurn = true; //Otherwise, it's the human player's turn
			}
			winnerCheck(human,computer,NUM_SHIP,grid); //Check if either party have won the game
		}		
		}
	}
}


