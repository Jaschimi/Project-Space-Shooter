package gameObjects;

//This class is the superclass of both the EnemyShips and the SpaceShooter.
public abstract class Spaceship extends ColoringField {

	protected int[] topLeftCorner = new int[2];
	protected int length;
	protected int height;
	protected int maxLives;
	protected int lives;
	protected int[][] cannons;
	protected Projectile[] shots;

	//Getters and Setters for various things
	public int[] getTopLeftCorner() {return topLeftCorner;}

	public int getLength() {return length;}
	public int getHeight() {return height;}

	public int getMaxLives() {return maxLives;}
	public int getLives() {return lives;}
	public void setLives(int lives) {this.lives = lives;}
	
	public int[][] getCannons() {return cannons;}
	public void setCannons(int[][] cannons) {this.cannons = cannons;}
	
	public Projectile[] getShots() {return shots;}
	public void setShots(Projectile[] shots) {this.shots = shots;}
	
	//Aside from a length and a height, Spaceships also have ammunition, lives and a top left corner.
	//Ammunition is the amount of projectiles a Spaceship can shoot at once, the top left corner determines where
	//the ship will spawn on the board and lives is the amount of times a ship can be hit by a projectile.
	public Spaceship(int[] topLeftCorner, int length, int height, int maxLives, int ammo){
		
		super(length, height);
		
	}
	
	//This method makes a Spaceship appear on the board
	public abstract void spawn();
	
	//This method makes a Spaceship shoot a projectile
	public abstract void shoot(int[] cannon);
	
	//This method makes a Spaceship move in one of four direction
	public abstract void move(char direction);
	
	//This method is triggered once a ship is hit by a projectile
	public abstract boolean hit();
	
	//This method is useful for making a SpaceShip fade away over time after it is destroyed
	public void fade(){
		//Every color
		for(int i=0;i<3;i++){
			//in every entry of the positions array
			for(int x=0;x<this.length;x++){
				for(int y=0;y<this.height;y++){
					//is reduced by one if it isn't zero.
					if(this.positions[x][y][i]!=0)this.positions[x][y][i]-=1;
					this.spawn();
				}
			}
		}
	}
}
