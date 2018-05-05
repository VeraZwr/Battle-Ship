
public class Item {
	private Type type;
	private boolean called;
	private Gamer owner;
	
	/**
	 * Creates an object of the item class with the specific type and owner
	 *
	 * @param owner The creator of the specific item
	 * @param  type The type of the item, defined in a seperate enum class
	 */
	public Item (Gamer owner, Type type){
		this.owner = owner;
		this.type = type;
		this.called = false;
	}
	/**
	 * Creates an object of the empty type to initialize before being assigned a owner and a type
	 */
	public Item() {
		this.owner = null;
		this.type = Type.EMPTY;
		this.called = false;
	}

	//Accessor
	/**
	 * Access the owner for a specific position in the grid
	 * @return a Gamer who owns this position
	 */
	public Gamer getOwner(){
		return this.owner;
	}
	/**
	 * Access the whether a specific position in the grid has been called before
	 * @return a boolean to tell if it has been called
	 */
	public boolean getCalled(){
		return this.called;
	}
	/**
	 * Access the type for a specific position in the grid
	 * @return a Type to tell the specific type of this position
	 */
	public Type getType(){
		return this.type;
	}
	//Mutator
	/**
	 * Change the owner of the item
	 *
	 * @param owner The owner of the item
	 */
	public void setOwner(Gamer owner){
		this.owner=owner;
	}
	/**
	 * Change the type of the item
	 *
	 * @param type The type of the item
	 */
	public void setType (Type type){
		this.type = type;
	}
	/**
	 * Change the the called attributes of the item
	 */
	public void hit() {
		this.called = true;
	}
	
	//toString
	/**
	 * print the item on the battlefield maps
	 * 
	 * @return a String used to print the item according to game rule
	 */
	 public String toString() {
		 
		 if (type == Type.EMPTY){
			 return "* ";
		 }
		 if (owner.getName().equals("human") && type == Type.GRENADE){
			 return "g ";
		 }
		 if (owner.getName().equals("human") && type == Type.SHIP){
			 return "s ";
		 }
		 if (owner.getName().equals("computer") && type == Type.GRENADE){
			 return "G ";
		 }
		 if (owner.getName().equals("computer") && type == Type.SHIP){
			 return "S ";
		 }
		return null;
		 }
}

