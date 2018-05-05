
public class Gamer {
	
	private String name;
	private int totalMissedTurn;
	private int additionalTurnDue;
	private int sunkShip;
	
	/**
	 * Access the name of a specific gamer
	 * @return a String with the name of the game
	 */
	public String getName() {
		return name;
	}
	/**
	 * Change the name of the gamer
	 *
	 * @param name the new name of the gamer
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Constructor method used to instantiate an gamer object
	 * at the beginning of a game
	 *  
	 * @param name the name of the gamer
	 */
	public Gamer (String name){
		this.setName(name);
		this.setTotalMissedTurn(0);
		this.setAdditionalTurnDue(0);
		this.setSunkShip(0);
	}
	/**
	 * Access the total missed turn statistics of a specific gamer
	 * 
	 * @return a integer indicating how many turns he has missed during this round of game
	 */
	public int getTotalMissedTurn(){
		return totalMissedTurn;
	}
	/**
	 * Access the total number of sunk ship of a specific player during game play
	 * 
	 * @return a integer indicating how many ships have been sunk during this round of game
	 */
	public int getSunkShip() {
		return this.sunkShip;
	}
	/**
	 * Change the number of sunken ship of a specific player
	 *
	 * @param sunkShip the new number of sunken ship of a player
	 */
	public void setSunkShip(int sunkShip) {
		this.sunkShip = sunkShip;
	}
	/**
	 * Access the number of additional turns due to a player because of opponent's calling on grenade
	 * 
	 * @return a integer indicating how many additional turns due on a player
	 */
	public int getAdditionalTurnDue() {
		return additionalTurnDue;
	}
	/**
	 * Change the number of additional turns due to a player because of opponent's calling on grenade
	 *
	 * @param additionalTurnDue the number of additional turns due on a player
	 */
	public void setAdditionalTurnDue(int additionalTurnDue) {
		this.additionalTurnDue = additionalTurnDue;
	}
	/**
	 * Change the total missed turn statistics of a specific gamer
	 *
	 * @param additionalTurnDue the new number of turns the gamer has missed during this round of game
	 */
	public void setTotalMissedTurn(int totalMissedTurn) {
		this.totalMissedTurn = totalMissedTurn;
	}


}
